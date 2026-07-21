# choi-indexer

크롤러가 수집한 뉴스를 MySQL에서 읽어 Elasticsearch에 색인하고, 검색 API를 제공하는 Spring Boot 애플리케이션입니다.

## 아키텍처

```
[크롤러] --(뉴스 저장)--> [MySQL: news 테이블]

[클라이언트] --POST /index {date}--> [IndexController]
                                          |
                                          v
                                   [IndexProducer] --발행--> [RabbitMQ: index.queue]
                                                                    |
                                                                    v
                                                            [IndexConsumer]
                                                                    |
                                                                    v
                                                            [IndexService]
                                                     MySQL 조회 -> Elasticsearch 색인

[클라이언트] --GET /search--> [SearchController] --> [SearchService] --> Elasticsearch 조회
```

`/index` 호출은 큐에 메시지만 넣고 바로 응답합니다. 실제 DB 조회와 Elasticsearch 색인은 `IndexConsumer`가 비동기로 처리합니다.

## 기술 스택

- Spring Boot 3.5 (Java 17)
- MySQL + Spring Data JPA
- Elasticsearch
- RabbitMQ (Spring AMQP)
- Springdoc OpenAPI (Swagger)
