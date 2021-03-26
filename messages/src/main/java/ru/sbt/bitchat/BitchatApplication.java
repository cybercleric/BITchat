package ru.sbt.bitchat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import static org.springframework.boot.SpringApplication.run;

@EnableRetry
@SpringBootApplication
public class BitchatApplication {
    public static void main(String[] args) {
        run(BitchatApplication.class, args);
    }
}
