package com.springbe.usersapi.service;

import com.springbe.usersapi.exception.CustomException;
import com.springbe.usersapi.model.User;
import com.springbe.usersapi.model.UserResponse;
import com.springbe.usersapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }


    @Retryable(
            value = {ResourceAccessException.class, HttpClientErrorException.class}, 
            maxAttemptsExpression = "${spring.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${spring.retry.backoff.delay}")
        )
        @CircuitBreaker(name = "userService", fallbackMethod = "fallbackForUserAPI")
 public List<User> loadUsersFromExternalAPI() {
    String url = "https://dummyjson.com/users";

    try {
        logger.info("Fetching users from external API: {}", url);
        UserResponse response = restTemplate.getForObject(url, UserResponse.class);

        if (response != null && response.getUsers() != null) {
            logger.info("Successfully fetched {} users", response.getUsers().size());

            List<User> users = response.getUsers()
                .stream()
                .peek(user -> user.setId(null)) // Reset ID to avoid conflicts
                .filter(user -> !userRepository.existsByEmail(user.getEmail())) // Skip duplicates
                .collect(Collectors.toList());

           

            return userRepository.saveAll(users);
        } else {
            logger.warn("API response was empty or null.");
            throw new CustomException("Failed to fetch users: API response was empty.");
        }
    } catch (HttpClientErrorException e) {
        logger.error("API returned HTTP {}: {}", e.getStatusCode(), e.getResponseBodyAsString());
        throw new CustomException("External API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
    } catch (ResourceAccessException e) {
        logger.error("Failed to reach external API: {}", e.getMessage());
        throw new CustomException("API is unreachable: " + e.getMessage());
    } catch (Exception e) {
        logger.error("Unexpected error: {}", e.getMessage(), e);
        throw new CustomException("Unexpected error: " + e.getMessage());
    }
}
@Recover
public List<User> fallbackLoadUsers(Exception e) {
    logger.error("External API is down. Returning empty user list. Error: {}", e.getMessage());
    return List.of(); // Return an empty list instead of failing the request
}

    public List<User> searchUsers(String query) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSsnContainingIgnoreCase(
            query, query, query);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
