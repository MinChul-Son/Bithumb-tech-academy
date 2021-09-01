package net.minchul.api.quiz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minchul.api.quiz.service.KafkaSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {
    private final KafkaSender kafkaSender;

    @GetMapping
    public String hello() {
        return "Hello Kafka";
    }

    @GetMapping("/producer")
    public String producer(@RequestParam("message") String message) {
        log.info("##### Producer Request #####");
        kafkaSender.send(message);
        return "Message Sent to Kafka Success!";
    }
}
