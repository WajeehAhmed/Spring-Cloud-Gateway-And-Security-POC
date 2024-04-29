package com.cipherLab.gatewayserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Configuration
public class GlobalFilterConfiguration implements GlobalFilter {
    @Bean
    @Order(0)
    public GlobalFilter prePostFilter() {
        return (exchange, chain) -> {
            log.info("Pre-filter is executing...");

            String requestPath = exchange.getRequest().getPath().toString();
            log.info("Request path = " + requestPath);

            HttpHeaders headers = exchange.getRequest().getHeaders();

            Set<String> headerNames = headers.keySet();

            headerNames.forEach((headerName)-> {

                String headerValue = headers.getFirst(headerName);
                log.info(headerName + " " + headerValue);

            });
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Post filter executed");
            }));
        };
    }

    @Bean
    @Order(1)
    public GlobalFilter prePostFilterSecond() {
        return (exchange, chain) -> {
            log.info("Pre-filter-2 is executing...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Post filter-2 executed");
            }));
        };
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }
}
