package com.example.indexer.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NewsRes {
    private int total;
    private List<News> res;

    public static NewsRes of(int total, List<News> news) {
        return NewsRes.builder()
            .total(total)
            .res(news)
            .build();
    }
}
