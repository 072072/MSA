package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {


    /**
     *  application.yml 파일의 설정을 java 로 셋팅할 수 있다
     *
     *  path 경로로 들어오면 uri+path 로 보냄
     *  filter 는  헤더값을 넣어서 서버로 보내거나 클라이언트 쪽으로 보냄
     */
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f-> f.addRequestHeader("first-request","first-request-header")
                                .addResponseHeader("first-response","first-response-header"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f-> f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response","second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}

