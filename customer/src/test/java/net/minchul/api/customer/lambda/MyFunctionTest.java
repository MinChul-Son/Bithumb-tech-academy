package net.minchul.api.customer.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class MyFunctionTest {

    @Test
    void main() {
        List<String> list = Arrays.asList("민철", "경우", "승훈", "우석");
        Function<String, Integer> function = String::length;
        List<Integer> map = map(list, function);
        for (Integer integer : map) {
            System.out.println("integer = " + integer);
        }
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> newList = new ArrayList<>();
        for (T t : list) {
            newList.add(function.apply(t));
        }
        return newList;
    }
}
