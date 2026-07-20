package com.example.indexer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.indexer.common.ApiResult;
import com.example.indexer.service.SearchService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/search")
@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping("")
    public ApiResult<?> getNews(
        @RequestParam(value = "start_date", defaultValue = "2026-05-01") String startDate,
        @RequestParam(value = "end_date", defaultValue = "2026-05-30") String endDate,
        @RequestParam(value = "category", defaultValue = "경제") String category,
        @RequestParam(value = "keyword", defaultValue = "삼성") String keyword,
        @RequestParam(value = "sort", defaultValue = "asc") String sort
    ) {
        return searchService.getNews(startDate, endDate, category, keyword, sort);
    }
}
