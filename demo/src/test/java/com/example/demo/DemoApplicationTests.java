package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.indexer.service.IndexService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class DemoApplicationTests {

	private final IndexService indexService;

	@Test
	void contextLoads() {
		indexService.save();
	}

}
