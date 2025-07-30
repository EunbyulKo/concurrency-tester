package org.silverstar.router;

import org.silverstar.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {

        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), helloHandler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> routeSleep(HelloHandler helloHandler) {

        return RouterFunctions
                .route(GET("/sleep").and(accept(MediaType.APPLICATION_JSON)), helloHandler::sleep);
    }

    @Bean
    public RouterFunction<ServerResponse> routeCalculate(HelloHandler helloHandler) {

        return RouterFunctions
                .route(GET("/calculate").and(accept(MediaType.APPLICATION_JSON)), helloHandler::calculate);
    }
}
