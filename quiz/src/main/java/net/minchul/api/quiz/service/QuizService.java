package net.minchul.api.quiz.service;

import lombok.RequiredArgsConstructor;
import net.minchul.api.quiz.domain.Attempt;
import net.minchul.api.quiz.domain.Quiz;
import net.minchul.api.quiz.domain.User;
import net.minchul.api.quiz.repository.AttemptRepository;
import net.minchul.api.quiz.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final GeneratorService generatorService;
    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;

    public Mono<Quiz> createQuiz() {
        int factorA = generatorService.randomFactor();
        int factorB = generatorService.randomFactor();
        Quiz quiz = new Quiz(factorA, factorB);
        return Mono.just(quiz);
    }

    public boolean checkAttempt(Attempt attempt) {
        // 존재하는 회원인지 체크
        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());
        Assert.isTrue(!attempt.isCorrect(), "Unable to send in graded state");
        boolean isCorrect = attempt.getResultAttempt() == attempt.getQuiz().getFactorA() * attempt.getQuiz().getFactorB();
        attemptRepository.save(new Attempt(attempt.getUser(), attempt.getQuiz(), attempt.getResultAttempt(), isCorrect));
        return isCorrect;
    }

    public Flux<Attempt> getStatsForUser(String alias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(alias);
    }

    public Mono<Attempt> getResultById(long id) {
        return null;
    }
}
