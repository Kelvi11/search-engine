package com.kelvin.searchengine;

import com.kelvin.searchengine.model.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentStorage {

    private Map<Integer, Document> documents = new HashMap<>();

    public Map<Integer, Document> getDocuments() {
        return documents;
    }

    public void store(Document document){
        documents.put(document.getId(), document);
    }
}
