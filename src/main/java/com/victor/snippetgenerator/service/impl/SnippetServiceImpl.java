package com.victor.snippetgenerator.service.impl;

import com.victor.snippetgenerator.models.SnippetRequest;
import com.victor.snippetgenerator.models.Snippet;
import com.victor.snippetgenerator.service.SnippetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SnippetServiceImpl implements SnippetService {

    private List<Snippet> snippetList = new ArrayList<>();

    @Override
    public Snippet createSnippet(SnippetRequest snippetRequest) {

        String url = "http://localhost:8080/snippets/" + snippetRequest.getName();
        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(snippetRequest.getExpiresIn());
        String snippetContent = snippetRequest.getSnippet();

        // If snippet already exists with the same name provided, it updates it with the new info provided
        if(snippetExists(snippetRequest.getName())){
            for (Snippet snippet: snippetList){
                if(snippet.getName().equals(snippetRequest.getName())){
                    snippet.setExpiresAt(expiresAt);
                    snippet.setSnippet(snippetContent);
                    return snippet;
                }
            }
        }

        // If snippet did not exist before, it creates a new one
        Snippet snippet = new Snippet(url, snippetRequest.getName(), expiresAt, snippetContent);
        snippetList.add(snippet);
        return snippet;
    }

    @Override
    public Snippet getSnippet(String snippetName) {
        if (!snippetExists(snippetName)) return null;
        for(Snippet snippet: snippetList){
            if(snippet.getName().equals(snippetName)){
                if(LocalDateTime.now().isAfter(snippet.getExpiresAt())){
                    return null;
                } else {
                    snippet.setExpiresAt(snippet.getExpiresAt().plusSeconds(30));
                    return snippet;
                }
            }
        }
        return null;
    }

    @Override
    public boolean snippetExists(String snippetName) {
        return snippetList.stream().anyMatch(snippet -> snippet.getName().equals(snippetName));
    }

    private Optional<Snippet> getSnippetByName(String snippetName){
        return snippetList.stream().filter(snippet -> snippet.getName().equals(snippetName)).findFirst();
    }


}
