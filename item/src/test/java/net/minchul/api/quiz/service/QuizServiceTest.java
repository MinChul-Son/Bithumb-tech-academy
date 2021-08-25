package net.minchul.api.quiz.service;

import net.minchul.api.quiz.domain.Quiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    private QuizService quizService;

    @Mock
    GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        quizService = new QuizService(generatorService);
    }

    @DisplayName("곱셈 : 50 * 30 = 1500")
    @Test
    void createQuiz() {

        BDDMockito.given(generatorService.randomFactor()).willReturn(50, 30);
        Quiz quiz = quizService.createQuiz();
        assertThat(quiz.getResult()).isEqualTo(1500);
    }
}
