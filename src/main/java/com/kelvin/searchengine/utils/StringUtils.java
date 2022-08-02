package com.kelvin.searchengine.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static boolean isAtLeastOneTokenNonAlphanumericCharacter(String tokensSentence) {
        List<String> tokens = Arrays.asList(tokensSentence.split(" "));

        for (String token : tokens){
            if(token.matches("^.*[^a-zA-Z0-9 ].*$")){
                return true;
            }
        }

        return false;
    }
}
