
Spring 기반의 다양한 실행 모델(MVC, WebFlux, VirtualThread)을 분리 모듈로 구성하고,
WebFlux의 기본 기능을 간단하게 활용하여
각 모델의 동작 방식 및 성능 특성을 실험하고 비교하기 위한 학습용 프로젝트입니다.

<br/>

---

<br/>

## 📚 사용 기술

- Java 17+
- Spring Boot 2.7.x
- Spring Web / WebFlux
- Gradle (멀티 모듈 구성)
- JMeter (성능 실험 도구)

<br/>

## 📦 모듈 구성

| 모듈명 | 설명 |
|--------|------|
| `domain-core` | 공통 비즈니스 로직 및 서비스 레이어를 담당합니다. 모든 API 모듈에서 참조합니다. |
| `spring-mvc-api` | Spring MVC의 REST API를 제공합니다. |
| `spring-webflux-api` | Spring WebFlux의 API를 제공합니다. |
| *(예정)* `virtualthread-api` | 가상 스레드 기반의 API를 구현할 예정입니다. (JDK 21 이상 필요) |

<br/>

---

<br/>

## 📍 `spring-mvc-api` 모듈

| 엔드포인트 | 목적 | 설명 |
|------------|------|------|
| `/spring-mvc/test` | 기본 동작 확인 | 시스템 기본 동작 테스트용 |
| `/spring-mvc/sleep` | I/O 비교 | `Thread.sleep()` 기반 |
| `/spring-mvc/calculate` | CPU연산 비교 | 오래 걸리는 계산 실행 |

<br/>

### 📍 `spring-webflux-api` 모듈

| 엔드포인트 | 목적 | 설명 |
|------------|------|------|
| `/spring-webflux/test` | 기본 동작 확인 | 기본 Mono/Flux 응답 확인용 |
| `/spring-webflux/sleep` | I/O 비교 | `delayElements()` 기반 |
| `/spring-webflux/calculate` | CPU연산 비교 | 오래 걸리는 계산 실행 |
| `/spring-webflux/stream` | Streaming 테스트 | `Flux`를 통해 여러 데이터 요소를 스트리밍 응답 |


> ✅ **참고**  
> - `Mono` 대신 `Flux`를 사용한 것은 **의도된 실험 설정**입니다.  
> - **이벤트 루프 내에서 블로킹하도록 배치**한 것도, **블로킹 상황에서 이벤트 루프가 어떻게 동작하는지를 보기 위한 실험 목적**입니다.  


<br/>

---

<br/>

## ⚙️ 성능 테스트

WebFlux가 CPU 연산보다 I/O 중심 작업에 더 적합하다는 가설을 검증하기 위해,  
**JMeter**를 사용하여 아래와 같은 테스트를 진행했습니다:

- 요청 수 / 초당 처리량 / 평균 응답 시간 비교
- `sleep`, `calculate` 엔드포인트를 통한 테스트

<br/>

#### 상세보기

- [성능 테스트 (IO)](/README_TEST_IO.md)  
- [성능 테스트 (CPU)](/README_TEST_CPU.md)

