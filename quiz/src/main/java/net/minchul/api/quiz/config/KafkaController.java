package net.minchul.api.quiz.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {
    private final KafkaProducer producer;

    @GetMapping
    public String hello() {
        return "Hello Kafka";
    }

    @PostMapping
    public String producer(@RequestParam("message") String message) {
        log.info("##### Producer Request #####");
        this.producer.sendMessage(message);
        return "Message Sent to Kafka Success!";
    }
}
