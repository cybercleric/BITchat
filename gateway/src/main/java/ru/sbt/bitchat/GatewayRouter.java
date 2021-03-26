package ru.sbt.bitchat;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.Math.max;
import static java.util.Optional.of;

@Configuration
public class GatewayRouter {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/**")
                        .filters(f -> f.changeRequestUri(s -> of(proxyByServiceName(s))))
                        .uri("http://unused")
                ).build();
    }

    private URI proxyByServiceName(ServerWebExchange s) {
        try {
            URI old = s.getRequest().getURI();
            String newPath = getPath(old);
            return new URI("http", old.getUserInfo(), "localhost", getPort(old), newPath, old.getQuery(), "");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPath(URI old) {
        if (old.getPath().startsWith("/backend")) {
            return old.getPath().substring(max(0, old.getPath().indexOf("/", 1)));
        }
        return old.getPath();
    }

    private int getPort(URI old) {
        if (old.getPath().startsWith("/backend")) return 8080;
        return 3000;
    }
}