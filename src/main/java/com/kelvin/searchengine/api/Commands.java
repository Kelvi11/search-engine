package com.kelvin.searchengine.api;

import com.kelvin.searchengine.model.Document;
import com.kelvin.searchengine.model.SearchEntity;
import com.kelvin.searchengine.repository.DocumentStorage;
import com.kelvin.searchengine.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class Commands {

    @Autowired
    DocumentStorage documentStorage;

    @ShellMethod("Adds a document to the index.")
    public String index(String docId, @ShellOption(arity = Integer.MAX_VALUE) String tokens) {

        int id;
        try {
            id = Integer.parseInt(docId);
        } catch (NumberFormatException e) {
            String message = "Doc id should be an integer!";
            return ResponseEntity.indexError(message);
        }

        if(id < 1){
            String message = "Doc id should be an positive integer!";
            return ResponseEntity.indexError(message);
        }

        if (tokens == null || tokens.isEmpty()) {
            String message = "At least one token should be provided!";
            return ResponseEntity.indexError(message);
        }

        if (StringUtils.isAtLeastOneTokenNonAlphanumericCharacter(tokens)) {
            String message = "Tokens should contain only alphanumeric characters!";
            return ResponseEntity.indexError(message);
        }

        Document document = new Document(id, tokens);
        documentStorage.store(document);

        return ResponseEntity.indexOk(id);
    }

    @ShellMethod("Search for document ids based on tokens.")
    public String query(@ShellOption(arity = Integer.MAX_VALUE) String expression) {

        expression = StringUtils.trimSpaces(expression);

        SearchEntity searchEntity = new SearchEntity(expression);

        List<Integer> documentsIds = new ArrayList<>(documentStorage.getDocumentsIds(searchEntity));

        if (documentsIds.isEmpty()) {
            String message = "No results found.";
            return ResponseEntity.queryError(message);
        }

        return ResponseEntity.queryResults(documentsIds);
    }
}
