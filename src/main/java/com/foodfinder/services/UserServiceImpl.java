package com.foodfinder.services;

import com.foodfinder.dtos.ApiResponseDto;
import com.foodfinder.dtos.ApiResponseStatus;
import com.foodfinder.dtos.SignUpRequestDto;
import com.foodfinder.exceptions.UserAlreadyExistsException;
import com.foodfinder.exceptions.UserNotFoundException;
import com.foodfinder.exceptions.UserServiceLogicException;
import com.foodfinder.models.User;
import com.foodfinder.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(SignUpRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException {

        // logic to register user
        try {
            if (userRepository.findByEmail(newUserDetails.getEmail()) != null){
                throw new UserAlreadyExistsException("Registration failed: User already exists with email " + newUserDetails.getEmail());
            }
            if (userRepository.findByUsername(newUserDetails.getUserName()) != null){
                throw new UserAlreadyExistsException("Registration failed: User already exists with username " + newUserDetails.getUserName());
            }

            User newUser = new User(
                    newUserDetails.getUserName(), newUserDetails.getEmail(), LocalDateTime.now()
            );

            // save() is an in built method given by JpaRepository
            userRepository.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            ApiResponseDto.builder()
                                    .isSuccess(true)
                                    .message("New user account has been successfully created!")
                                    .build()
                    );

        }catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }catch (Exception e) {
            log.error("Failed to create new user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        // logic to get all users
        try {
            List<User> users = userRepository.findAllByOrderByRegDateAndTimeDesc();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ApiResponseDto.builder()
                                    .isSuccess(true)
                                    .response(users)
                                    .build()
                    );

        }catch (Exception e) {
            log.error("Failed to fetch all users: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(SignUpRequestDto newUserDetails, int id)
            throws UserNotFoundException, UserServiceLogicException {
        // logic to update user
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

            user.setEmail(newUserDetails.getEmail());
            user.setUsername(newUserDetails.getUserName());
            user.setPhone(newUserDetails.getPhone());

            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ApiResponseDto.builder()
                                    .isSuccess(true)
                                    .message("User account updated successfully!")
                                    .build()
                    );

        }catch(UserNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }catch(Exception e) {
            log.error("Failed to update user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(int id) throws UserServiceLogicException, UserNotFoundException {
        // logic to delete user
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

            userRepository.delete(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ApiResponseDto.builder()
                                    .isSuccess(true)
                                    .message("User account deleted successfully!")
                                    .build()
                    );

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete user account: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

}
