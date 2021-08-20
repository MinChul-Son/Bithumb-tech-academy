package net.minchul.api.customer.lambda;

@FunctionalInterface
public interface MyUnaryOperator {

    int operator(Integer a);
}
