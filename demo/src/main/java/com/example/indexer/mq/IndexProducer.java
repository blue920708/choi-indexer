package com.example.indexer.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.indexer.config.RabbitMQConfig;

import lombok.RequiredArgsConstructor;

// 메시지 발행자(Producer) - 큐에 메시지를 밀어넣는 역할만 하고 실제 처리는 하지 않음
@Component
@RequiredArgsConstructor
public class IndexProducer {

    // RabbitTemplate: RabbitMQ 브로커와 통신하는 스프링 표준 클라이언트
    private final RabbitTemplate rabbitTemplate;

    public void send(String date) {
        // RabbitMQConfig.INDEX_QUEUE 큐로 date 문자열을 메시지로 발행
        rabbitTemplate.convertAndSend(RabbitMQConfig.INDEX_QUEUE, date);
    }
}
