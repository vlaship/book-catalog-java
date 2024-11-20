package dev.vlaship.book.catalog.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.hc5.ApacheHttp5Client;
import org.springframework.context.annotation.Bean;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.time.Duration;
import java.util.List;

public class BookFeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        List.of().toArray();
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return (s, response) -> switch (response.status()) {
            case 400 -> new RuntimeException("Bad Request");
            case 404 -> new RuntimeException("Not Found");
            default -> new RuntimeException("Generic error");
        };
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("apiKey", "apiKey");
            template.header("Accept", "application/json");
        };
    }

    @Bean
    public Client client() {
        return new ApacheHttp5Client();
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                Duration.ofSeconds(5),
                Duration.ofSeconds(30),
                true
        );
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public Decoder customDecoder(ObjectMapper objectMapper) {
        return new JacksonDecoder(objectMapper);
    }

    @Bean
    public Encoder customEncoder(ObjectMapper objectMapper) {
        return new JacksonEncoder(objectMapper);
    }
}
