package org.silverstar.handler;

import lombok.RequiredArgsConstructor;
import org.silverstar.service.WorkService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class HelloHandler {

    private final WorkService workService;

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello, Spring!"));
    }

    public Mono<ServerResponse> sleep(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Flux.just("sleep")
                        .delayElements(Duration.ofMillis(3000))
                        .log(), String.class));
    }

    public Mono<ServerResponse> calculate(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Flux.just("calculate")
                        .map(t -> workService.calculateLongTime())
                        .log(), String.class));
    }

}
