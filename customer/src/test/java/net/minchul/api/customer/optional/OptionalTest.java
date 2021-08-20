package net.minchul.api.customer.optional;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class OptionalTest {

    @Data
    class Address {
        private final String street;
        private final String city;
        private final String zipcode;
    }

    @Data
    class Member {
        private final Long id;
        private final String name;
        private final Address address;
    }


    @Data
    class Order {
        private final Long id;
        private final Date date;
        private final Member member;
    }

    public String getCity(Order order) {
        return Optional.ofNullable(order)
                .map(Order::getMember)
                .map(Member::getAddress)
                .map(Address::getCity)
                .orElse("도시가 없는데요?");
    }

    public Optional<Member> getMember(Order order, int min) {
        return Optional.ofNullable(order)
                .filter(o -> o.getDate().getTime() > System.currentTimeMillis() - min * 1000)
                .map(Order::getMember);
    }

    @Test
    void getCityTest() {
        Address address = new Address("어느대로", "무슨도시", "123");
        Address nullAddress = new Address("어느대로", null, "123");
        Member member1 = new Member(1l, "민철", address);
        Member member2 = new Member(1l, "경우", nullAddress);
        Order order1 = new Order(1l, new Date(), member1);
        Order order2 = new Order(1l, new Date(), member2);

        assertThat(getCity(order1)).isEqualTo("무슨도시");
        assertThat(getCity(order2)).isEqualTo("도시가 없는데요?");
    }
}
