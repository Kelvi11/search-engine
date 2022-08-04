package com.kelvin.searchengine.api;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseEntity {

    public static String indexOk(int docId) {
        return "index ok " + docId;
    }

    public static String indexError(String message) {
        return "index error " + message;
    }

    public static String queryResults(List<Integer> documentsIds) {

        String message = documentsIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        return "\nquery results " + message;
    }

    public static String queryError(String message) {
        return "\nquery error " + message;
    }
}
