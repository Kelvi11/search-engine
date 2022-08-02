package com.kelvin.searchengine;

import com.kelvin.searchengine.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.List;

@ShellComponent
public class Commands {

    @Autowired
    DocumentStorage documentStorage;

    @ShellMethod("Adds a document to the index.")
    public String index(int docId, @ShellOption(arity = Integer.MAX_VALUE) String tokens) {

        if(tokens == null || tokens.isEmpty()){
            String message = "At least one token should be provided!";
            return ResponseEntity.indexError(message);
        }

        Document document = new Document(docId, tokens);
        documentStorage.store(document);

        return ResponseEntity.indexOk(docId);
    }

    @ShellMethod("Search for document ids based on tokens.")
    public String query(@ShellOption(arity = Integer.MAX_VALUE) String expression) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(expression);

        return stringBuilder.toString();
    }
}
