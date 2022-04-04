package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter(){
        super(LoggingFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(LoggingFilter.Config config) {
        //Custom pre Filter
/*        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter: request id -> {}", config.getBaseMessage());
            if(config.isPreLogger()){
                log.info("Logging PRE Filter : request id -> {}", request.getId());
            }
            //Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{ // Mono -> 웹플럭스??라고 스프링5에서 추가.. 비동기방식의 서버를 지원할때 단일값을 전달할때 사용
                if(config.isPostLogger()){
                    log.info("Logging POST Filter: response id -> {}", response.getStatusCode());
                }
            }));
        });*/   // 두개 같음 위는 람다, 아래는 푼것
        GatewayFilter filter = new OrderedGatewayFilter((exchange,chain)-> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter: request id -> {}", config.getBaseMessage());
            if(config.isPreLogger()){
                log.info("Logging PRE Filter : request id -> {}", request.getId());
            }
            //Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{ // Mono -> 웹플럭스??라고 스프링5에서 추가.. 비동기방식의 서버를 지원할때 단일값을 전달할때 사용
                if(config.isPostLogger()){
                    log.info("Logging POST Filter: response id -> {}", response.getStatusCode());
                }
            }));
        }, Ordered.LOWEST_PRECEDENCE );
        // 두번째 매개변수 필터의 우선순위를 정함  HIGHEST_PRECEDENCE-> 가장먼저 실행, LOWEST_PRECEDENCE-> 가장 마지막에 실행
        return filter;
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}