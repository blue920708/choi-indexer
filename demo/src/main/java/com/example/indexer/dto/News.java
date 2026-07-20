package com.example.indexer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
    private String url;
    private String title;
    private String content;
    private String date;
    private String category;
}
