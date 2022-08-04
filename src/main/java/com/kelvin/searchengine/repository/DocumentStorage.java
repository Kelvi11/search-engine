package com.kelvin.searchengine.repository;

import com.kelvin.searchengine.model.Document;
import com.kelvin.searchengine.model.SearchEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DocumentStorage {

    private Map<Integer, Document> documents = new HashMap<>();

    public DocumentStorage() {
        populateDocumentsWithDummyData();
    }

    private void populateDocumentsWithDummyData() {
        this.documents.put(1, new Document(1, "soup tomato cream salt"));
        this.documents.put(2, new Document(2, "cake sugar eggs flour sugar cocoa cream butter"));
        this.documents.put(3, new Document(3, "soup fish potato salt pepper"));
    }

    public void store(Document document){
        documents.put(document.getId(), document);
    }

    public List<Integer> getDocumentsIds(SearchEntity searchEntity) {

        Predicate<Map.Entry<Integer, Document>> predicate = null;

        for (int i = 0; i < searchEntity.getTokens().size(); i++){

            if (i == 0){
                int index = i;
                predicate = d -> d.getValue().getTokens().contains(searchEntity.getTokens().get(index));
            }
            else {
                if (searchEntity.getSymbols().get(i - 1).equals('&')){
                    int index = i;
                    predicate = predicate.and(d -> d.getValue().getTokens().contains(searchEntity.getTokens().get(index)));
                }
                else if (searchEntity.getSymbols().get(i - 1).equals('|')){
                    int index = i;
                    predicate = predicate.or(d -> d.getValue().getTokens().contains(searchEntity.getTokens().get(index)));
                }
            }
        }

        return documents.entrySet()
                .stream()
                .filter(predicate)
                .map(d -> d.getKey())
                .collect(Collectors.toList());
    }
}
