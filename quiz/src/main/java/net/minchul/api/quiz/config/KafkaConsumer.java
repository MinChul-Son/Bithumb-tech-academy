package net.minchul.api.quiz.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "sample", groupId = "myGroup")
    public void consume(String message) throws IOException {
        log.info("Consumer Message :{}", message);
    }
}
