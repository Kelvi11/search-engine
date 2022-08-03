package com.kelvin.searchengine.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SearchToken {

    private List<String> tokens;
    private List<Character> symbols;

    public SearchToken(String expression) {

        this.tokens = new ArrayList<>();
        this.symbols = new ArrayList<>();

        char[] chars = expression.toCharArray();
        int latestAlphaNumericCharacterIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            Character character = chars[i];

            if (character.equals('(')) {
                latestAlphaNumericCharacterIndex++;
                continue;
            }

            if (character.equals('|') || character.equals('&')) {
                symbols.add(character);
                if (i > 0 && chars[i - 1] != ')') {
                    String token = expression.substring(latestAlphaNumericCharacterIndex, i);
                    tokens.add(token);
                }
                latestAlphaNumericCharacterIndex = i + 1;
                continue;
            }
            if (character.equals(')')) {
                String token = expression.substring(latestAlphaNumericCharacterIndex, i);
                tokens.add(token);
                latestAlphaNumericCharacterIndex = i + 1;
                continue;
            }

            if (i == chars.length - 1) {
                String token = expression.substring(latestAlphaNumericCharacterIndex, i + 1);
                tokens.add(token);
            }
        }
    }
}
