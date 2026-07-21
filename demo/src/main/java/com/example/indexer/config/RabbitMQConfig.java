package com.example.indexer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// RabbitMQ 큐 정의 - 애플리케이션 기동 시 브로커에 큐가 없으면 자동으로 생성됨
@Configuration
public class RabbitMQConfig {

    // Producer/Consumer가 공통으로 참조하는 큐 이름
    public static final String INDEX_QUEUE = "index.queue";

    @Bean
    public Queue indexQueue() {
        return new Queue(INDEX_QUEUE, true);
    }
}
