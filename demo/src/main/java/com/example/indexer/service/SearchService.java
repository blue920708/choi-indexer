package com.example.indexer.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.indexer.common.ApiCode;
import com.example.indexer.common.ApiResult;
import com.example.indexer.dto.NewsRes;
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
            String sort,
            Integer size,
            Integer page
    ) {
        SortOrder order =
            "asc".equals(sort)
                ? SortOrder.Asc
                : SortOrder.Desc;

        int pageSize = size != null ? size : 10;

        try {
            SearchResponse<NewsDocument> result =
                client.search(s -> {
                    s.index("news");
                    s.size(pageSize);
                    s.trackTotalHits(t -> t.enabled(true)); // 1만 건 넘어가도 정확한 total 카운트

                    if (page != null && page > 1) {
                        s.from((page - 1) * pageSize);
                    }

                    return s.query(q -> q
                        .bool(b -> {
                            if (keyword != null && !keyword.isBlank()) {
                                b.must(m -> m
                                    .multiMatch(mm -> mm
                                        .query(keyword)
                                        .fields("title", "content")
                                    )
                                );
                            }

                            if (category != null && !category.isBlank()) {
                                b.filter(f -> f
                                    .term(t -> t
                                        .field("category")
                                        .value(category)
                                    )
                                );
                            }

                            if (startDate != null || endDate != null) {
                                b.filter(f -> f
                                    .range(r -> r
                                        .date(d -> {
                                            d.field("date");
                                            if (startDate != null) {
                                                d.gte(startDate);
                                            }
                                            if (endDate != null) {
                                                d.lte(endDate);
                                            }
                                            return d;
                                        })
                                    )
                                );
                            }

                            return b;
                        })
                    )
                    .sort(srt -> srt
                        .field(f -> f
                            .field("date")
                            .order(order)
                        )
                    );
                }, NewsDocument.class);

            List<NewsDocument> list = result.hits()
                .hits()
                .stream()
                .map(hit -> hit.source())
                .toList();

            long total = result.hits().total() != null
                ? result.hits().total().value()
                : list.size();

            return new ApiResult<>(NewsRes.of(total, list));

        } catch (ElasticsearchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResult(ApiCode.FAIL);
    }
}
