package net.minchul.api.customer.stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StreamTest {

    @Test
    void streamEmptyTest() {
        Stream<String> stream = Arrays.asList("사과", "오렌지", "").stream();
        int count = (int) stream.filter(String::isEmpty).count();
        assertThat(count).isEqualTo(1);
    }
}
