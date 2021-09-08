package net.minchul.api.producer.controller;

import lombok.RequiredArgsConstructor;
import net.minchul.api.producer.domain.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "kafka-spring-producer";

    @GetMapping("/publish/{name}")
    public String postMessage(@PathVariable final String name) {
        User user = new User();
        user.setId(String.valueOf(UUID.randomUUID()));
        user.setName(name);
        user.setEmail(name + "@test.com");
        kafkaTemplate.send(TOPIC, user);
        return "Message Published Success!";
    }
}
