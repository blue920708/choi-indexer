package com.example.indexer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.indexer.config.RabbitMQConfig;

import lombok.extern.slf4j.Slf4j;

// index.queue에서 재시도(max-attempts)를 다 소진하고 넘어온 메시지를 처리
@Slf4j
@Component
public class IndexDeadLetterConsumer {

    @RabbitListener(queues = RabbitMQConfig.INDEX_DLQ)
    public void consume(String date) {
        log.error("인덱싱 최종 실패 - 날짜: {} (재시도 모두 실패, DLQ로 이동)", date);
    }
}
