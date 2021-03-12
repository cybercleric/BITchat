package ru.sbt.bitchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class BitchatApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitchatApplication.class, args);
    }
}
