package com.transmarket.app.apigateway;

import com.transmarket.app.apigateway.filters.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator transMarketRouteLocator(RouteLocatorBuilder routeLocatorBuilder, AuthenticationFilter authFilter) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/api/v1/cargos/**")
                        .filters(f -> f.filter(authFilter).rewritePath("/api/v1/cargos/(?<segment>.*)", "/api/v1/cargos/${segment}")
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
                                .circuitBreaker(config -> config.setName("cargosCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://CARGO-SERVICE"))
                .route(p -> p
                        .path("/api/v1/auth/**")
                        .filters(f -> f.filter(authFilter).rewritePath("/api/v1/auth/(?<segment>.*)", "/api/v1/auth/${segment}")
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
                                .circuitBreaker(config -> config.setName("cargosCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://USER-SERVICE"))
                .route(p -> p
                        .path("/api/v1/companies/**")
                        .filters(f -> f.filter(authFilter).rewritePath("/api/v1/companies/(?<segment>.*)", "/api/v1/companies/${segment}")
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
                                .circuitBreaker(config -> config.setName("companiesCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://COMPANY-SERVICE"))
                .route(p -> p
                        .path("/api/v1/vehicles/**")
                        .filters(f -> f.filter(authFilter).rewritePath("/api/v1/vehicles/(?<segment>.*)", "/api/v1/vehicles/${segment}")
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
                                .circuitBreaker(config -> config.setName("vehiclesCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://VEHICLE-SERVICE"))
                .route(p -> p
                        .path("/api/v1/offers/**")
                        .filters(f -> f.filter(authFilter).rewritePath("/api/v1/offers/(?<segment>.*)", "/api/v1/offers/${segment}")
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
                                .circuitBreaker(config -> config.setName("offersCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://OFFER-SERVICE"))
                .route(p -> p
                        .path("/api/v1/users/**")
                        .filters(f -> f.filter(authFilter).rewritePath("/api/v1/users/(?<segment>.*)", "/api/v1/users/${segment}")
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
                                .circuitBreaker(config -> config.setName("usersCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://USER-SERVICE"))
                .build();
    }
    @Bean
    public RedisRateLimiter redisRateLimiter(){
        return new RedisRateLimiter(1,1,1);
    }
    @Bean
    KeyResolver userKeyResolver(){
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }
}
