package com.example.indexer.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.indexer.dto.document.NewsDocument;

public interface  IndexRepository extends ElasticsearchRepository<NewsDocument, String> {
}
