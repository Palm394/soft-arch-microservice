package com.se3.project.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping
@EnableAutoConfiguration
public class ApiGatewayApplication {
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/user-service/**")
						.filters(f -> f
								.rewritePath("^/user-service", "")
								.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://USER-SERVICE"))
				.route(r -> r.path("/reservation-service/**")
						.filters(f -> f
								.rewritePath("^/reservation-service", "")
								.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://RESERVATION-SERVICE"))
				.route(r -> r.path("/parking-lots-service/**")
						.filters(f -> f
								.rewritePath("^/parking-lots-service", "")
								.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://PARKING-LOTS-SERVICE"))
				.route(r -> r.path("/review-service/**")
						.filters(f -> f
								.rewritePath("^/review-service", "")
								.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://REVIEW-SERVICE"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
