package net.minchul.api.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minchul.api.consumer.domain.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @KafkaListener(topics = "kafka-spring-producer", containerFactory = "userFactory")
    public void listenHeaders(@Payload User user,
                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received Message :{}",user);
        log.info("From Partition :{}",partition);
    }
}
