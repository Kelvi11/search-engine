package com.kelvin.searchengine.api;

public class ResponseEntity {

    public static String indexOk(int docId) {
        return "index ok " + docId;
    }

    public static String indexError(String message) {
        return "index error " + message;
    }

    public static String queryResults() {
        return "query results ";
    }

    public static String queryError() {
        return "query error ";
    }
}
