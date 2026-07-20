package com.example.indexer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// RabbitMQ 큐 정의 - 애플리케이션 기동 시 브로커에 큐가 없으면 자동으로 생성됨
@Configuration
public class RabbitMQConfig {

    // Producer/Consumer가 공통으로 참조하는 큐 이름
    public static final String INDEX_QUEUE = "index.queue";

    // 재시도(max-attempts)를 다 소진하고도 실패한 메시지가 최종적으로 쌓이는 큐
    public static final String INDEX_DLQ = "index.queue.dlq";
    private static final String INDEX_DLX = "index.queue.dlx";

    @Bean
    public Queue indexQueue() {
        // 재시도가 모두 실패해 메시지가 reject되면 x-dead-letter-exchange로 라우팅되도록 지정
        return QueueBuilder.durable(INDEX_QUEUE)
            .withArgument("x-dead-letter-exchange", INDEX_DLX)
            .withArgument("x-dead-letter-routing-key", INDEX_DLQ)
            .build();
    }

    @Bean
    public Queue indexDeadLetterQueue() {
        return new Queue(INDEX_DLQ, true);
    }

    @Bean
    public DirectExchange indexDeadLetterExchange() {
        return new DirectExchange(INDEX_DLX);
    }

    @Bean
    public Binding indexDeadLetterBinding() {
        return BindingBuilder.bind(indexDeadLetterQueue())
            .to(indexDeadLetterExchange())
            .with(INDEX_DLQ);
    }
}
