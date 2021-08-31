package net.minchul.api.quiz.service;

import lombok.RequiredArgsConstructor;
import net.minchul.api.quiz.domain.Quiz;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final GeneratorService generatorService;

    public Mono<Quiz> createQuiz() {
        int factorA = generatorService.randomFactor();
        int factorB = generatorService.randomFactor();
        Quiz quiz = new Quiz(factorA, factorB);
        return Mono.just(quiz);
    }
}
