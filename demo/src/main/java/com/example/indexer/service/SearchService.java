package com.example.indexer.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.indexer.common.ApiCode;
import com.example.indexer.common.ApiResult;
import com.example.indexer.dto.document.NewsDocument;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final ElasticsearchClient client;

    public ApiResult<?> getNews(
            String startDate,
            String endDate,
            String category,
            String keyword,
            String sort
    ) {
        SortOrder order =
            "asc".equals(sort)
                ? SortOrder.Asc
                : SortOrder.Desc;

        try {
            SearchResponse<NewsDocument> result =
                client.search(s -> s
                    .index("news")
                    .query(q -> q
                        .bool(b -> b
                            .must(m -> m
                                .multiMatch(mm -> mm
                                    .query(keyword)
                                    .fields("title", "content")
                                )
                            )
                            .filter(f -> f
                                .term(t -> t
                                    .field("category")
                                    .value(category)
                                )
                            )
                            .filter(f -> f
                                .range(r -> r
                                    .date(d -> d
                                        .field("date")
                                        .gte(startDate)
                                        .lte(endDate)
                                    )
                                )
                            )
                        )
                    )
                    .sort(srt -> srt
                        .field(f -> f
                            .field("date")
                            .order(order)
                        )
                    )
                    , NewsDocument.class
                );

            List<NewsDocument> list = result.hits()
                .hits()
                .stream()
                .map(hit -> hit.source())
                .toList();

            return new ApiResult<>(list);

        } catch (ElasticsearchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResult(ApiCode.FAIL);
    }
}
