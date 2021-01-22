package com.victor.snippetgenerator.controllers;

import com.victor.snippetgenerator.models.SnippetRequest;
import com.victor.snippetgenerator.models.Snippet;
import com.victor.snippetgenerator.service.SnippetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private SnippetService snippetService;

    public SnippetController(SnippetService snippetService){
        this.snippetService = snippetService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSnippet (@RequestBody SnippetRequest snippetRequest){
        Snippet snippet = snippetService.createSnippet(snippetRequest);
        return ResponseEntity.created(URI.create(snippet.getUrl())).body(snippet);
    }

    @GetMapping(value = "/{snippetName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSnippet (@PathVariable("snippetName") String snippetName) {
        Snippet snippet = snippetService.getSnippet(snippetName);
        if(snippet == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry... The snippet with " + snippetName + " does not exist or has expired");
        return ResponseEntity.ok(snippet);
    }
}
