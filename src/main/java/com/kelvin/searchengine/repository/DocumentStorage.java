package com.kelvin.searchengine.repository;

import com.kelvin.searchengine.model.Document;
import com.kelvin.searchengine.model.SearchToken;
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

    public List<Integer> getDocumentsIdsForSingleToken(String expression) {

        return documents.entrySet()
                .stream()
                .filter(d -> d.getValue().getTokens().contains(expression))
                .map(d -> d.getKey())
                .collect(Collectors.toList());
    }

    public List<Integer> getDocumentsIds(SearchToken searchToken) {

        Predicate<Map.Entry<Integer, Document>> predicate = null;

        for (int i = 0; i < searchToken.getTokens().size(); i++){

            if (i == 0){
                int finalI = i;
                predicate = d -> d.getValue().getTokens().contains(searchToken.getTokens().get(finalI));
            }
            else {
                if (searchToken.getSymbols().get(i - 1).equals('&')){
                    int finalI1 = i;
                    predicate = predicate.and(d -> d.getValue().getTokens().contains(searchToken.getTokens().get(finalI1)));
                }
                else if (searchToken.getSymbols().get(i - 1).equals('|')){
                    int finalI2 = i;
                    predicate = predicate.or(d -> d.getValue().getTokens().contains(searchToken.getTokens().get(finalI2)));
                }
            }
        }

        return documents.entrySet()
                .stream()
                .filter(predicate)
                .map(d -> d.getKey())
                .collect(Collectors.toList());
    }

    public void store(Document document){
        documents.put(document.getId(), document);
    }
}
