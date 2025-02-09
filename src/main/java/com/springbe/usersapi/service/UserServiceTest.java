//package com.springbe.usersapi.service;
//
//import com.springbe.usersapi.model.User;
//import com.springbe.usersapi.model.UserResponse;
//import com.springbe.usersapi.repository.UserRepository;
//import io.github.resilience4j.circuitbreaker.CircuitBreaker;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private UserService userService;
//
//    private CircuitBreaker circuitBreaker;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
//        circuitBreaker = circuitBreakerRegistry.circuitBreaker("userService");
//    }
//
//    @Test
//    void testLoadUsersFromExternalAPI_Success() {
//        // Mock API response
//        UserResponse mockResponse = new UserResponse();
//        User mockUser = new User();
//        mockUser.setFirstName("John");
//        mockUser.setLastName("Doe");
//        mockUser.setEmail("john.doe@example.com");
//        mockResponse.setUsers(List.of(mockUser));
//
//        when(restTemplate.getForObject(anyString(), eq(UserResponse.class)))
//                .thenReturn(mockResponse);
//        when(userRepository.saveAll(anyList())).thenReturn(List.of(mockUser));
//
//        List<User> users = userService.loadUsersFromExternalAPI();
//
//        assertNotNull(users);
//        assertEquals(1, users.size());
//        assertEquals("John", users.get(0).getFirstName());
//    }
//
//    @Test
//    void testLoadUsersFromExternalAPI_Failure_ShouldRetry() {
//        // Simulate API failure
//        when(restTemplate.getForObject(anyString(), eq(UserResponse.class)))
//                .thenThrow(new RuntimeException("API error"));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            userService.loadUsersFromExternalAPI();
//        });
//
//        assertTrue(exception.getMessage().contains("Unexpected error while fetching users"));
//        verify(restTemplate, times(3)).getForObject(anyString(), eq(UserResponse.class)); // Retries 3 times
//    }
//
//    @Test
//    void testCircuitBreaker_OpensOnFailures() {
//        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("userService");
//
//        // Simulating repeated failures
//        for (int i = 0; i < 10; i++) {
//            try {
//                userService.loadUsersFromExternalAPI();
//            } catch (Exception ignored) {}
//        }
//
//        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
//    }
//
//    @Test
//    void testFallbackMethodWhenAPIIsDown() {
//        List<User> fallbackUsers = userService.fallbackForUserAPI(new RuntimeException("API failure"));
//        assertNotNull(fallbackUsers);
//        assertTrue(fallbackUsers.isEmpty());
//    }
//}
