package com.springbe.usersapi.repository;

import com.springbe.usersapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSsnContainingIgnoreCase(
        String firstName, String lastName, String ssn);
}
