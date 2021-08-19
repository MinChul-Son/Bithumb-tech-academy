package net.minchul.api.customer.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MySupplierTest {

    @Test
    void main() {
        Supplier<String> stringSupplier = () -> new String("안녕 Supplier");

        assertThat(stringSupplier.get(), is(equalTo("안녕 Supplier")));
    }
}