package net.minchul.api.customer.lambda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MyInterfaceTest {

    @DisplayName("Imperative vs Declarative")
    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("1부터 100사이의 짝수의 합")
    void sumTest() {
        // Imperative(명령형)
        int sumOfEvens = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                sumOfEvens = sumOfEvens + i;
            }
        }
        System.out.println("Imperative(명령형) = " + sumOfEvens);

        // Declarative(선언형) = Functional
        sumOfEvens = IntStream.rangeClosed(0, 100)
                .filter(i -> i % 2 == 0)
                .reduce((x, y) -> x + y)
                .getAsInt();

        System.out.println("Declarative(선언형) = " + sumOfEvens);
    }

    static String myFunction(MyInterface fun) { return fun.myMethod(); }

    @Test
    @DisplayName("함수형 인터페이스")
    void myMethodTest() {
        assertThat(myFunction(() -> "안녕 Functional Interface"),
                is(equalTo("안녕 Functional Interface")));
    }
}