
# 테스트 - I/O로 인한 지연

<br/>

## 🧪 가설
- 응답 시간은 항상 3초 이상 소요된다 (대기 시간 고정).
- WebFlux는 더 많은 동시 요청을 처리할 수 있다 (논블로킹).
- WebFlux에서 Thread.sleep()을 사용하면 MVC보다도 느릴 수 있다.

<br/>

## 🧩 비교 대상

| 분류 | 설명 |
|------|------|
| **Spring MVC + Thread.sleep** | 서블릿 스레드를 직접 점유하는 전통적인 방식 |
| **Spring WebFlux** | 논블로킹 방식으로 `Mono.delay()` 또는 `delayElements()` 사용 |




<br/>

## 📋 테스트 케이스

요청 수, 반복 횟수, 지속 시간을 조합하여 다음과 같은 부하 시나리오를 구성했습니다.

| 케이스 | 동시 사용자 수 | 반복 횟수 | 테스트 시간 |
|--------|----------------|------------|---------------|
| Case 1 | 10명 | 1회 | 1초 |
| Case 2 | 1000명 | 1회 | 5초 |
| Case 3 | 1000명 | 3회 | 5초 |
| Case 4 | 2000명 | 1회 | 1초 |

<br/>


## 📊 결과

### ✅ Case 1: 10명 × 1회 × 1초

#### Spring MVC + `Thread.sleep`
![IO_case1_mvc](/IO_case1_mvc.png)

#### Spring WebFlux
![IO_case1_webflux](/IO_case1_webflux.png)


---

### ✅ Case 2: 1000명 × 1회 × 5초

#### Spring MVC + `Thread.sleep`
![IO_case2_mvc](/IO_case2_mvc.png)

#### Spring WebFlux
![IO_case2_webflux](/IO_case2_webflux.png)



---

### ✅ Case 3: 1000명 × 3회 × 5초

#### Spring MVC + `Thread.sleep`
![IO_case3_mvc](/IO_case3_mvc.png)

#### Spring WebFlux
![IO_case3_webflux](/IO_case3_webflux.png)



---

### ✅ Case 4: 2000명 × 1회 × 1초

#### Spring MVC + `Thread.sleep`
![IO_case4_mvc](/IO_case4_mvc.png)

#### Spring WebFlux
![IO_case4_webflux](/IO_case4_webflux.png)




<br/>

## 🧪 번외 테스트

```java
@GetMapping("/spring-webflux/blocking-sleep")
public Mono<String> blockingSleep() {
    try {
        Thread.sleep(3000); // 블로킹
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    return Mono.just("sleep");
}
```

MVC보다 더 느려질 수 있음을 확인

#### Spring WebFlux + `Thread.sleep`
![IO_case1_webflux_thread](/IO_case2_webflux_thread.png)

#### Spring WebFlux + `Thread.sleep`
![IO_case4_webflux_thread](/IO_case4_webflux_thread.png)




