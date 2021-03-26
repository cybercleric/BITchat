package ru.sbt.bitchat;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class GatewayStarter {
    public static void main(String[] args) {
        run(GatewayStarter.class, args);
    }
}
