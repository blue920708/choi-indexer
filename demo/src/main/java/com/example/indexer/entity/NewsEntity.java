package com.example.indexer.entity;

import org.springframework.beans.BeanUtils;

import com.example.indexer.dto.News;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String title;
    private String content;
    private String date;
    private String category;

    public static NewsEntity of(News news) {
        NewsEntity entity = new NewsEntity();
        BeanUtils.copyProperties(news, entity);
        return entity;
    }
}