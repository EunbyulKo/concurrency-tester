
# 테스트 - CPU 연산으로 인한 지연

<br/>

## 🧪 가설
- CPU 연산은 논블로킹 I/O와 달리 WebFlux의 구조적 이점이 크게 작용하지 않는다. 

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
| Case 1 | 10명 | 1회 | 5초 |

<br/>


## 📊 결과

### ✅ Case 1: 10명 × 1회 × 5초

#### Spring MVC
![CPU_case1_mvc](/CPU_case1_mvc.png)

#### Spring WebFlux
![CPU_case1_webflux](/CPU_case1_webflux.png)


