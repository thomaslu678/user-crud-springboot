package com.foodfinder.dataSeeders;

import com.foodfinder.models.ERole;
import com.foodfinder.models.Role;
import com.foodfinder.models.User;
import com.foodfinder.repositories.RoleRepository;
import com.foodfinder.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomUserDataSeeder {
    @Autowired
    private UserRepository userRepository;

    @EventListener
    @Transactional
    public void LoadUsers(ContextRefreshedEvent event) {

        User newUser = User.builder()
                .id(4L)
                .username("james")
                .email("james@abc.com")
                .password(new BCryptPasswordEncoder().encode("jamesjames"))
                .build();

        userRepository.save(newUser);

    }

}
