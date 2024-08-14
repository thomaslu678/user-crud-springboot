package com.foodfinder.services;

import com.foodfinder.dtos.ApiResponseDto;
import com.foodfinder.dtos.UserDetailsRequestDto;
import com.foodfinder.exceptions.UserAlreadyExistsException;
import com.foodfinder.exceptions.UserNotFoundException;
import com.foodfinder.exceptions.UserServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllUsers()
            throws UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateUser(UserDetailsRequestDto newUserDetails, int id)
            throws UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteUser(int id)
            throws UserServiceLogicException, UserNotFoundException;

}