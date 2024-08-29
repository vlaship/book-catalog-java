package dev.vlaship.book.catalog.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.time.Duration;

@ConfigurationProperties(prefix = "client.rest-client")
public record RestClientProperties(
        String baseUrl,
        String path,
        Duration socketTimeout,
        Duration connectionTimeout,

        @NestedConfigurationProperty
        RetryConfiguration retry,

        @NestedConfigurationProperty
        PoolingConfiguration pooling
) {
}

record RetryConfiguration(
        int count,
        int interval
) {
}

record PoolingConfiguration(
        int maxTotal,
        int defaultMaxPerRoute
) {
}
