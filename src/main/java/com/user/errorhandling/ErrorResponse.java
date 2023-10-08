package com.user.errorhandling;


import java.util.ArrayList;
import java.util.List;

import com.user.User;
import com.user.constants.Constants;

public class ErrorResponse extends Constants{
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {

        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

public static ErrorResponse validateUserInput(User user) {
    ErrorResponse errorResponse = new ErrorResponse();

    if (user.getRoleId() <= 0) {
        errorResponse.addError(INVALID_ROLE_ID_FORMAT);
    }

    if (user.getUserName() == null || !user.getUserName().matches("[A-Za-z]+")) {
        errorResponse.addError(INVALID_USERNAME_FORMAT);
    }

    if (user.getEmailAddress() == null || !user.getEmailAddress().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
        errorResponse.addError(INVALID_EMAIL_FORMAT);
    }

    String phoneNumber = Long.toString(user.getPhoneNumber());
    if (phoneNumber == null || !phoneNumber.matches("[6-9]\\d{9}")) {
        errorResponse.addError(INVALID_PHONE_FORMAT);
    }
    return errorResponse;
}
}