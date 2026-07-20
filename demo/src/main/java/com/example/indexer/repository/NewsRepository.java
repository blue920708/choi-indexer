package com.example.indexer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.indexer.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    List<NewsEntity> findByDate(String date);

}
