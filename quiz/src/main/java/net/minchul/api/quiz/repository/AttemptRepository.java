package net.minchul.api.quiz.repository;


import net.minchul.api.quiz.domain.Attempt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AttemptRepository extends ReactiveMongoRepository<Attempt, Long> {
    Flux<Attempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
