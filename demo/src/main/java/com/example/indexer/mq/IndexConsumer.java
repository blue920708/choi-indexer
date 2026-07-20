package com.example.indexer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.indexer.config.RabbitMQConfig;
import com.example.indexer.service.IndexService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Consumer - 큐에 메시지가 들어오면 스프링이 이 메서드를 자동으로 호출
@Slf4j
@Component
@RequiredArgsConstructor
public class IndexConsumer {

    private final IndexService indexService;

    @RabbitListener(queues = RabbitMQConfig.INDEX_QUEUE)
    public void consume(String date) {
        log.info("메시지 수신 - 날짜: {}", date);
        indexService.save(date);
    }
}
