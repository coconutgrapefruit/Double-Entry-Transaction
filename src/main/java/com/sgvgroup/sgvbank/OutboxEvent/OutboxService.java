package com.sgvgroup.sgvbank.OutboxEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final OutboxEventRepository repository;
    private final KafkaTemplate<String,String> kafka;

    @Scheduled(fixedDelay = 5000)
    public void publishPendingEvents() {
        repository.findByPublishedFalse()
                .forEach(event -> {
                    kafka.send("sgvTopic", event.getPayload());
                    event.setPublished(true);
                    repository.save(event);
                });
    }
}
