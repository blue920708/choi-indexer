package com.example.indexer.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NewsRes<T> {
    private long total;
    private List<T> res;

    public static <T> NewsRes<T> of(long total, List<T> news) {
        return NewsRes.<T>builder()
            .total(total)
            .res(news)
            .build();
    }
}
