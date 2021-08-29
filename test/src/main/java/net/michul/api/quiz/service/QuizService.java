package net.michul.api.quiz.service;

import lombok.RequiredArgsConstructor;
import net.michul.api.quiz.domain.Quiz;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final GeneratorService generatorService;

    public Quiz createQuiz() {
        return new Quiz(
                generatorService.randomFactor(),
                generatorService.randomFactor()
        );
    }
}
