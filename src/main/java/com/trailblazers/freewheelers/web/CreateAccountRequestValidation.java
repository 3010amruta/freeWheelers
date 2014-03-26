package com.trailblazers.freewheelers.web;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by kumarvis on 26/03/14.
 */
public class CreateAccountRequestValidation {



    public static HashMap<String, String> verifyInputs(HttpServletRequest createAccountRequest) {

        HashMap<String, String> errors = new HashMap<String, String>();
        if(createAccountRequest.getParameter("password").isEmpty()) {
            errors.put("password", "Password can't be empty");
        }
        else if(!isPasswordValid(createAccountRequest.getParameter("password"))) {
            errors.put("validPassword", "Must enter a valid password!");
        }
        return errors;
    }

    private static boolean isPasswordValid(String password) {
        boolean hasUppercaseAndLowerCase = !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase());
        boolean isBetween8And20Characters = password.length() >= 8 && password.length() <= 20;
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        boolean hasDigits = false;
//        hasDigits = containsDigit(hasDigits);

        if (!(hasUppercaseAndLowerCase && hasSpecial && isBetween8And20Characters && hasDigits)){
            return false;
        }
        return true;
    }

//    private boolean containsDigit(boolean hasDigits) {
//        for (int characterNumber = 0; characterNumber < password.length(); characterNumber++) {
//            if (Character.isDigit(password.charAt(characterNumber))){
//                hasDigits = true;
//                break;
//            }
//        }
//        return hasDigits;
//    }
}
