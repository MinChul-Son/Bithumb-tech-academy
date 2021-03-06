package net.minchul.api.quiz.example;

import net.minchul.api.quiz.config.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class FluxAndMonoTest {

    @Mock
    CustomException customExceptionMono;

    @Mock
    CustomException customExceptionFlux;

    @BeforeEach
    void setUp() {
        customExceptionMono = new CustomException("Mono");
        customExceptionFlux = new CustomException("Flux");
    }

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

    @DisplayName("Mono.just() 예제")
    @Test
    void monoJust() {
        /**
         * Reactive Stream 에서는 Data, Event, Signal 중에서 Signal 을 사용한다.
         * onNext, onComplete, onError
         * */
        List<Signal<Integer>> list = new ArrayList<>(4);
        final Integer[] result = new Integer[1];
        Mono<Integer> mono = Mono.just(1).log()
                .doOnEach(i -> {
                    list.add(i);
                    System.out.println("Signal = " + i);
                });
        mono.subscribe(i -> result[0] = i);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0).getType().name()).isEqualTo("ON_NEXT");
        assertThat(list.get(1).getType().name()).isEqualTo("ON_COMPLETE");
        assertThat(result[0].intValue()).isEqualTo(1);
    }

    @DisplayName("Mono.empty() 예제")
    @Test
    void monoEmpty() {
        List<String> list = new ArrayList<>();
        Mono<String> mono = Mono.empty();
        mono.subscribe(list::add);
        assertThat(list).isEmpty();
    }

    @DisplayName("Mono.just 예제2")
    @Test
    void monoJust2() {
        System.out.println("------ Mono.empty() ------");
        Mono.empty().subscribe(System.out::print);
        System.out.println("------ Mono.just() ------");
        Mono.just("Java")
                .map(item -> "Mono item: " + item)
                .subscribe(System.out::print);
    }

    @DisplayName("Mono Flux error() 예제")
    @Test
    void error() {
        Mono.error(customExceptionMono)
                .doOnError(e -> System.out.println("Mono inside doOnError"))
                .subscribe();
        Flux.error(customExceptionFlux)
                .doOnError(e -> System.out.println("Flux inside doOnError"))
                .subscribe();
    }
}
