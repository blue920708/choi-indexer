package com.example.indexer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.indexer.common.ApiResult;
import com.example.indexer.dto.IndexReq;
import com.example.indexer.mq.IndexProducer;

import lombok.RequiredArgsConstructor;

@RequestMapping("/index")
@RequiredArgsConstructor
@RestController
public class IndexController {

    private final IndexProducer indexProducer;

    @PostMapping("")
    public ApiResult<?> index(@RequestBody IndexReq req) {
        // 큐에 넣기만 하고 바로 응답 -> 실제 DB 조회/인덱싱은 IndexConsumer가 비동기로 처리
        indexProducer.send(req.getDate());
        return new ApiResult<>(req.getDate());
    }
}
