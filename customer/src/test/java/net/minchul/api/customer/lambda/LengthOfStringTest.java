package net.minchul.api.customer.lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class LengthOfStringTest {

    @Test
    @DisplayName("문자를 입력받아 문자의 길이를 출력")
    void lengthOfStringTest() {
        LengthOfString length = str -> {
            int len = str.length();
            System.out.println("문자열의 길이 : " + len);
            return len;
        };
        assertThat(length.main("몇 글자게~요?"), is(equalTo(8)));
    }
}