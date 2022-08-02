package com.kelvin.searchengine;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {

    @ShellMethod("Adds a document to the index.")
    public String index(int docId, @ShellOption(arity = Integer.MAX_VALUE) String tokens) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(docId);
        stringBuilder.append(" ");
        stringBuilder.append(tokens);
        stringBuilder.append(" ");

        return stringBuilder.toString();
    }

    @ShellMethod("Search for document ids based on tokens.")
    public String query(@ShellOption(arity = Integer.MAX_VALUE) String expression) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(expression);

        return stringBuilder.toString();
    }
}
