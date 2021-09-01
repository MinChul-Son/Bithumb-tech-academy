package net.minchul.api.quiz.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.minchul.api.quiz.domain.Attempt;
import net.minchul.api.quiz.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public class AttemptController {
    private final QuizService quizService;

    @PostMapping
    ResponseEntity<Attempt> postResult(@RequestBody Attempt attempt) {
        boolean isCorrect = quizService.checkAttempt(attempt);
        return ResponseEntity.ok(new Attempt(attempt.getUser(), attempt.getQuiz(), attempt.getResultAttempt(), isCorrect));
    }

    @GetMapping
    ResponseEntity<Flux<Attempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(quizService.getStatsForUser(alias));
    }

    @GetMapping("/{id}")
    ResponseEntity<Mono<Attempt>> getResultById(final @PathVariable("id") long id) {
        return ResponseEntity.ok(quizService.getResultById(id));
    }


    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    static final class ResultResponse {
        private final boolean correct;
    }
}
