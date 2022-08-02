package com.kelvin.searchengine;

import com.kelvin.searchengine.model.Document;
import com.kelvin.searchengine.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {

    @Autowired
    DocumentStorage documentStorage;

    @ShellMethod("Adds a document to the index.")
    public String index(String docId, @ShellOption(arity = Integer.MAX_VALUE) String tokens) {

        int id;
        try {
            id = Integer.parseInt(docId);
        }
        catch (NumberFormatException e){
            String message = "Doc id should be an integer!";
            return ResponseEntity.indexError(message);
        }

        if(tokens == null || tokens.isEmpty()){
            String message = "At least one token should be provided!";
            return ResponseEntity.indexError(message);
        }

        if (StringUtils.isAtLeastOneTokenNonAlphanumericCharacter(tokens)){
            String message = "Tokens should contain only alphanumeric characters!";
            return ResponseEntity.indexError(message);
        }

        Document document = new Document(id, tokens);
        documentStorage.store(document);

        return ResponseEntity.indexOk(id);
    }

    @ShellMethod("Search for document ids based on tokens.")
    public String query(@ShellOption(arity = Integer.MAX_VALUE) String expression) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(expression);

        return stringBuilder.toString();
    }
}
