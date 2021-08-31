package net.minchul.api.quiz.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class FluxAndMonoTest {

    @DisplayName("Flux.just() 예제")
    @Test
    void just() {
        /**
         *  block 상태의 List는 원소를 출력하기 위해 for문을 돌면서 하나씩 찍어야함.
         *  non-block 방식의 Flux는 데이터가 계속 흐르고있는 상태이므로 뒤에 .log()만으로 하나씩 찍어낼 수 있음.
         */
        List<String> names = new ArrayList<>(); // block
        Flux<String> flux = Flux.just("손민철", "이경우", "강승훈", "강우석").log(); // non-block
        flux.subscribe(names::add); // List인 names에 flux 원소 하나씩 추가
        assertThat(names).isEqualTo(Arrays.asList("손민철", "이경우", "강승훈", "강우석"));
    }

    @DisplayName("Flux.range() 예제")
    @Test
    void range() {
        List<Integer> numbers = new ArrayList<>();
        Flux<Integer> flux = Flux.range(1, 5).log();
        flux.subscribe(numbers::add);
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5);
    }

    @DisplayName("Flux.fromArray() 예제")
    @Test
    void fromArray() {
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.fromArray(new String[]{"손민철", "이경우", "강승훈", "강우석"}).log();
        flux.subscribe(names::add);
        assertThat(names).isEqualTo(Arrays.asList("손민철", "이경우", "강승훈", "강우석"));

        String[] strings = {"손민철", "이경우", "강승훈", "강우석"};
        Flux<String> flux2 = Flux.fromArray(strings).log();
        flux.subscribe();
    }

    @DisplayName("Flux.fromIterable() 예제")
    @Test
    void fromIterable() {
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.fromIterable(Arrays.asList("손민철", "이경우", "강승훈", "강우석")).log();
        flux.subscribe(names::add);
        assertThat(names).isEqualTo(Arrays.asList("손민철", "이경우", "강승훈", "강우석"));
    }

    @DisplayName("Flux.fromStream() 예제")
    @Test
    void fromStream() {
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.fromStream(Arrays.asList("손민철", "이경우", "강승훈", "강우석").stream()).log();
        flux.subscribe(names::add);
        assertThat(names).isEqualTo(Arrays.asList("손민철", "이경우", "강승훈", "강우석"));
    }

    @DisplayName("Flux.generator() 예제")
    @Test
    void generator() {
        Flux<String> flux = Flux.generate(
                () -> {
                    return 0;
                },
                (state, sink) -> {
                    sink.next("3 X " + state + " = " + 3 * state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }
        );
        flux.subscribe(System.out::println);
    }

    @DisplayName("Flux.create() 예제")
    @Test
    void create() {
        Flux<Integer> flux = Flux.create((FluxSink<Integer> sink) -> {
            sink.onRequest(request -> {
                for (int i = 1; i < request + 3; i++) { // subscriber 가 요청한 것보다 3개 더 발생
                    sink.next(i);
                }
            });
        });
        flux.subscribe(System.out::println);
    }

    @DisplayName("Flux.empty() 예제")
    @Test
    void empty() {
        List<String> list = new ArrayList<>();
        Flux<String> flux = Flux.empty();
        flux.subscribe(list::add);
        assertThat(list).isEmpty();
    }
}
