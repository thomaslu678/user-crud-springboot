package com.foodfinder.services;

import com.foodfinder.dtos.ApiResponseDto;
import com.foodfinder.dtos.SignUpRequestDto;
import com.foodfinder.exceptions.UserAlreadyExistsException;
import com.foodfinder.exceptions.UserNotFoundException;
import com.foodfinder.exceptions.UserServiceLogicException;
import com.foodfinder.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<ApiResponseDto<?>> registerUser(SignUpRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllUsers()
            throws UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateUser(SignUpRequestDto newUserDetails, int id)
            throws UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteUser(int id)
            throws UserServiceLogicException, UserNotFoundException;

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void save(User user);

}