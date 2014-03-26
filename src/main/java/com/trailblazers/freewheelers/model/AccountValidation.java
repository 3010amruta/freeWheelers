package com.trailblazers.freewheelers.model;

import java.util.HashMap;

public class AccountValidation {

    private static final int MAX_PHONE_NUMBER_DIGITS = 9;

    public static HashMap<String, String> verifyInputs(Account account) {
        HashMap<String, String> errors = new HashMap<String, String>();

        if (!account.getEmail_address().contains("@")) {
           errors.put("email", "Must enter a valid email!");
        }

        if(account.getPassword().isEmpty()) {
            errors.put("password", "Password can't be empty");
        }
        else if(!account.isPasswordValid()) {
            errors.put("validPassword", "Must enter a valid password!");
        }

        if(account.getAccount_name().isEmpty()) {
            errors.put("name", "Must enter a name!");
        }

        if(account.getPhoneNumber().isEmpty()) {
            errors.put("phoneNumber", "Phone number should contain only digits and must be nine digits long!");
        }

        if(!isPhoneNumberValid(account.getPhoneNumber())) {
            errors.put("phoneNumber", "Phone number should contain only digits and must be nine digits long!");
        }

        if(account.getCountry_id() == 0) {

            errors.put("country", "Must enter a country name!");
        }

        return errors;
    }


    private static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^\\d*$") && phoneNumber.length() == MAX_PHONE_NUMBER_DIGITS;
    }
}