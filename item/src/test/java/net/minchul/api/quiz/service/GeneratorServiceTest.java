package net.minchul.api.quiz.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeneratorServiceTest {

    @Mock
    GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        generatorService = new GeneratorService();
    }

    @DisplayName("랜덤 값 발생 로직 테스트")
    @Test
    void randomFactor() {

        List<Integer> randoms = IntStream.range(0, 1000)
                .map(i -> generatorService.randomFactor())
                .boxed()
                .collect(Collectors.toList());

        assertThat(randoms).containsOnlyElementsOf(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
    }
}