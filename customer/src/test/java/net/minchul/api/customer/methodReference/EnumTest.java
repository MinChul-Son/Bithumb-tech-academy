package net.minchul.api.customer.methodReference;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleBinaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EnumTest {

    @RequiredArgsConstructor
    enum Operation {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y),
        TIMES("*", (x, y) -> x * y),
        DIVIDE("/", (x, y) -> x / y);
        private final String symbol;
        private final DoubleBinaryOperator op;

        @Override
        public String toString() {
            return symbol;
        }

        public double apply(double x, double y) {
            return op.applyAsDouble(x, y);
        }
    }

    @Test
    void enumTest() {
        assertThat(Operation.PLUS.apply(5, 7), is(equalTo(12.0)));
        assertThat(Operation.MINUS.apply(5, 7), is(equalTo(-2.0)));
        assertThat(Operation.TIMES.apply(5, 7), is(equalTo(35.0)));
        assertThat(Operation.DIVIDE.apply(10, 2), is(equalTo(5.0)));
    }
}
