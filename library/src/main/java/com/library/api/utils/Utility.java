package com.library.api.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("ALL")
@Slf4j
public class Utility {

    public static boolean validEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (email.matches(emailRegex))
            return true;
        else
            return false;
    }


    public static boolean isNumeric(String number) {
        boolean isValid = false;

        String expression = "^[-+]?[0-9]*\\.?[0-9]+$";
        CharSequence inputStr = number;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean validateName(String name) {
        String pattern = "^[a-zA-Z-']*$";
        return name.matches(pattern);
    }

    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        if (s.matches(pattern)) {
            return true;
        }
        return false;
    }

    public static boolean validatePhoneNumber(String unsafePhone) {

        Pattern pattern = Pattern.compile("\\d+"); //accepts only digits
        Matcher matcher = pattern.matcher(unsafePhone);

        boolean valid = false;

        if (matcher.matches()) {
            valid = true;
        }
        return valid;
    }

    public static boolean containsAlphabet(String unsafeInput) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
        Matcher matcher = pattern.matcher(unsafeInput);
        boolean valid = false;
        if (matcher.matches()) {
            valid = true;
        }
        return valid;
    }

}
