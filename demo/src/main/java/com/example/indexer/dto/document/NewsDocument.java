package com.example.indexer.dto.document;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.example.indexer.entity.NewsEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(indexName = "news")
@JsonIgnoreProperties(ignoreUnknown = true) // Spring Data Elasticsearch가 저장 시 붙이는 _class 메타 필드를 무시
public class NewsDocument {
    @Id
    private Long id;
    private String url;
    private String title;
    private String content;
    private String date;
    private String category;

    public static NewsDocument of(NewsEntity entity) {
        NewsDocument document = new NewsDocument();
        BeanUtils.copyProperties(entity, document);
        return document;
    }
}
