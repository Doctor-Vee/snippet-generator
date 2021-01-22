package com.victor.snippetgenerator.models;

import lombok.Data;

@Data
public class SnippetRequest {

    String name;

    Integer expiresIn;

    String snippet;

}
