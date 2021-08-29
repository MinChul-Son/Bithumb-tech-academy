package net.michul.api.quiz.service;

import net.michul.api.quiz.domain.Factor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Function;

@Service
public class GeneratorService {

    public int randomFactor() {
        Function<String, Integer> function = Integer::parseInt;
        return new Random().nextInt(function.apply(Factor.MAXIMUM.toString())
                - function.apply(Factor.MINIMUM.toString()) + 1)
                + function.apply(Factor.MINIMUM.toString());
    }
}
