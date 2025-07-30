package org.silverstar.controller;

import lombok.RequiredArgsConstructor;
import org.silverstar.service.WorkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class SpringWebfluxController {

   private final WorkService workService;

    @GetMapping("/spring-webflux/test")
    public Flux<String> test() {
        return Flux.just("test");
    }

    @GetMapping("/spring-webflux/sleep")
    public Flux<String> sleep() {

        return Flux.just("sleep")
        .delayElements(Duration.ofMillis(3000))
                .log();
    }

    @GetMapping("/spring-webflux/stream")
    public Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // Java8의 무한 Stream
        return Flux.fromStream(stream.limit(10)) // Limit 제외
                .map(i -> Collections.singletonMap("value", i));
    }

    @GetMapping("/spring-webflux/calculate")
    public Flux<String> calculate() {
        return Flux.just("calculate")
                .map(t -> workService.calculateLongTime())
                .log();
    }

}
