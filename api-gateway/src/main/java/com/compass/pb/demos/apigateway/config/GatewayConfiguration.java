//package com.compass.pb.demos.apigateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfiguration {
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Authorization", "Bearer 1231223132123"))
//                        .uri("http://httpbin.org:80"))
//                .route(p ->  p
//                        .path("/customer/**")
//                        .uri("lb://customer-service"))
//                .route(p -> p
//                        .path("/pet/**")
//                        .uri("lb://pet-service"))
//                .build();
//    }
//}
