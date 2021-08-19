package net.minchul.api.customer.lambda;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MathOperationTest {

    @Test
    void main() {
        MathOperation plus = (a, b) -> a + b;
        MathOperation multiply = (a, b) -> a * b;

        assertThat(plus.main(5, 3), is(equalTo(8)));
        assertThat(multiply.main(5, 3), is(equalTo(15)));
    }
}