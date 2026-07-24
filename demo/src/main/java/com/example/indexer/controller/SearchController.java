package com.example.indexer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.indexer.common.ApiResult;
import com.example.indexer.service.SearchService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RequestMapping("/search")
@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping("")
    public ApiResult<?> getNews(
        @Parameter(example = "2026-05-01") @RequestParam(value = "start_date", required = false) String startDate,
        @Parameter(example = "2026-05-30") @RequestParam(value = "end_date", required = false) String endDate,
        @Parameter(example = "경제") @RequestParam(value = "category", required = false) String category,
        @Parameter(example = "삼성") @RequestParam(value = "keyword", required = false) String keyword,
        @Parameter(example = "asc") @RequestParam(value = "sort", required = false) String sort,
        @Parameter(example = "10") @RequestParam(value = "size", required = false) Integer size,
        @Parameter(example = "1") @RequestParam(value = "page", required = false) Integer page
    ) {
        return searchService.getNews(startDate, endDate, category, keyword, sort, size, page);
    }
}
