package com.foodfinder.repositories;

import com.foodfinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Developers can define methods in repository interfaces with custom query keywords,
    // and Spring Data JPA automatically translates them into appropriate SQL queries.
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    User findByUsername(String userName);

    List<User> findAllByOrderByRegDateAndTimeDesc();

}
