package org.silverstar.controller;

import lombok.RequiredArgsConstructor;
import org.silverstar.service.WorkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpringMvcController {

    private final WorkService workService;

    @GetMapping("/spring-mvc/test")
    public String test() {
        return "test";
    }

    @GetMapping("/spring-mvc/sleep")
    public String sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "sleep";
    }

    @GetMapping("/spring-mvc/calculate")
    public String calculate() {
        workService.calculateLongTime();
        return "calculate";
    }

}
