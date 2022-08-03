package com.kelvin.searchengine.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Document {

    private int id;
    private List<String> tokens;

    public Document(){}

    public Document(int id, String tokensSentence){
        this.id = id;
        this.tokens = Arrays.asList(tokensSentence.split(" "));
    }
}
