package com.se3.project.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
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
				.route(r -> r.path("/dump/**")
						.filters(f -> f.rewritePath("^/dump", ""))
						.uri("lb://DUMP-SERVICE"))
				.route(r -> r.path("/user/**")
						.filters(f -> f.rewritePath("^/user", ""))
						.uri("lb://USER-SERVICE"))
				.build();
	}
	@GetMapping("/service")
	public String dump() throws Exception {
		return "dumP";
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
