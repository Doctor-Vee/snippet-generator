package com.victor.snippetgenerator.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {

    String url;

    String name;

    LocalDateTime expiresAt;

    String snippet;


}
