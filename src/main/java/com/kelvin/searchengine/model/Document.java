package com.kelvin.searchengine.model;

import java.util.Arrays;
import java.util.List;


public class Document {

    private int id;
    private List<String> tokens;

    public Document(){}

    public Document(int id, String tokensSentence){
        this.id = id;
        this.tokens = Arrays.asList(tokensSentence.split(" "));
    }

    public int getId() {
        return id;
    }

    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", tokens=" + tokens +
                '}';
    }
}
