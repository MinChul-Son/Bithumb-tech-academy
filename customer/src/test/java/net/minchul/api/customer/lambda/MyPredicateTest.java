package net.minchul.api.customer.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MyPredicateTest {

    @Test
    void test1() {
        List<String> list = new ArrayList<>();
        list.add("민철");
        list.add("");
        list.add("스프링");
        list.add("");
        list.add("백엔드");

        Predicate<String> predicate = s -> !s.isEmpty();
        List<String> newList = filterList(list, predicate);
        for (String s : newList) {
            System.out.println("s = " + s);
        }
        assertThat(newList, is(equalTo(Arrays.asList("민철", "스프링", "백엔드"))));

        Predicate<String> filter = s -> s.contains("민");
        assertThat(filterList(list, filter), is(equalTo(Arrays.asList("민철"))));

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> integerFilter = e -> e % 2 == 0;
        assertThat(filterList(integerList, integerFilter), is(equalTo(Arrays.asList(2, 4))));

    }

    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }
        return newList;
    }
}