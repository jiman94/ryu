# otel 통한 로그 수집 테스트

Otel 에이전트를 통해 로그를 수집하는 테스트를 진행합니다.
로그에는 Otel 이 다른 시스템과의 통신 (http, grpc, database 등) 에서 수집한 정보를 포함합니다.

## 테스트 환경

collector 부팅

```shell
cd resources/otel-mdc-test
docker-compose up
```

Spring boot3 라서 Java 17 에서 가능함

```shell
% java -version
java version "17.0.6" 2023-01-17 LTS
Java(TM) SE Runtime Environment (build 17.0.6+9-LTS-190)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.6+9-LTS-190, mixed mode, sharing)
```

테스트 앱 빌드 및 실행

```shell
cd ../../
./gradlew build
java -javaagent:resources/otel-mdc-test/opentelemetry-javaagent.jar -jar front/build/libs/application.jar
```

다음과 같이 아무 경로 http 호출을 했을 때 트레이싱 정보와 함께 로그가 출력되는지 확인합니다.

```shell
curl localhost:9000/health

2024-03-11 15:33:46.720 trace_id=366169b3010e2df4b57ce1f22891f3df span_id=7a143d2c0648d34d trace_flags=01 WARN  [http-nio-9000-exec-3] o.s.w.s.PageNotFound: No mapping for GET /health
2024-03-11 15:33:46.765 trace_id=366169b3010e2df4b57ce1f22891f3df span_id=7a143d2c0648d34d trace_flags=01 ERROR [http-nio-9000-exec-3] c.m.m.c.w.a.ApiControllerExceptionHandler: GET '/health' - No endpoint GET /health.
org.springframework.web.servlet.NoHandlerFoundException: No endpoint GET /health.
```


