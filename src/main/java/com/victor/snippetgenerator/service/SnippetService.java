package com.victor.snippetgenerator.service;

import com.victor.snippetgenerator.models.SnippetRequest;
import com.victor.snippetgenerator.models.Snippet;

public interface SnippetService {

    Snippet createSnippet(SnippetRequest snippetRequest);

    Snippet getSnippet(String snippetName);

    Snippet likeSnippet(String snippetName);

    boolean snippetExists(String snippetName);

}
