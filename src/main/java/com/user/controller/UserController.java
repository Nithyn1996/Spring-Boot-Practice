package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.user.User;
import com.user.constants.Constants;
import com.user.errorhandling.ErrorResponse;
import com.user.dao.UserDao; // Import the UserDao

@RestController
@RequestMapping("api/v1/users") // Set a base URL for all user-related endpoints
public class UserController extends Constants {

    @Autowired
    private UserDao userDao; // Inject the UserDao

    @RequestMapping(method = RequestMethod.GET)
    public String welcome() {
        return "Welcome to the page";
    }

    @RequestMapping(value="/user", method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @RequestMapping(value="/user", method=RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        ErrorResponse errorResponse = ErrorResponse.validateUserInput(user);

        if (!errorResponse.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            User addedUser = userDao.addUser(user);
            int addedId = addedUser.getId();
            if (addedId > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INSERT_ERROR);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(INSERT_EXCEPTION_ERROR + e.getMessage());
        }
    }

    
    @RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        ErrorResponse errorResponse = ErrorResponse.validateUserInput(user);

        if (!errorResponse.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            user.setId(id);
            int rowsAffected = userDao.updateUser(user);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UPDATE_ERROR);
            }
        } catch (DataAccessException e) {
            String errorMessage = UPDATE_EXCEPTION_ERROR + "\n Exception: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    
    @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        try {
            int rowsAffected = userDao.deleteUser(id);
            if (rowsAffected > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESS);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DELETE_ERROR);
            }
        } catch (DataAccessException e) {
            String errorMessage = DELETE_EXCEPTION_ERROR + " \n Exception: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
