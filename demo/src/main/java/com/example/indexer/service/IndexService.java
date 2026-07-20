package com.example.indexer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.indexer.dto.document.NewsDocument;
import com.example.indexer.entity.NewsEntity;
import com.example.indexer.repository.IndexRepository;
import com.example.indexer.repository.NewsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexService {
    private final IndexRepository indexRepository;
    private final NewsRepository newsRepository;

    @Transactional
    public void save(String date) {
        // db조회
        List<NewsEntity> newsList = newsRepository.findByDate(date);

        // 인덱싱
        List<NewsDocument> documents = newsList.stream()
                .map(news -> NewsDocument.of(news))
                .collect(Collectors.toList());
        indexRepository.saveAll(documents);

        log.info("인덱싱 완료 - 날짜: {}, 저장건수: {}건", date, documents.size());
    }
}
