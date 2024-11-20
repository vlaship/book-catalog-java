package dev.vlaship.book.catalog.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AuthorFeignClientConfig {
    @Bean
    public Client feignClient() {
        return new feign.okhttp.OkHttpClient(okHttpClient());
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(chain -> {
                var request = chain.request().newBuilder()
                    .addHeader("Custom-Header", "Value")
                    .build();
                return chain.proceed(request);
            })
            .build();
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
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return (_, response) -> switch (response.status()) {
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
