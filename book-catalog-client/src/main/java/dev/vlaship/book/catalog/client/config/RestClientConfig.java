package dev.vlaship.book.catalog.client.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Profile("apache")
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({RestClientProperties.class})
public class RestClientConfig {

    @Bean("clientConnectionManager")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(RestClientProperties clientProperties) {
        var connectionManager = new PoolingHttpClientConnectionManager();
        var connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.of(clientProperties.connectionTimeout()))
                .setSocketTimeout(Timeout.of(clientProperties.socketTimeout()))
                .build();
        connectionManager.setDefaultConnectionConfig(connectionConfig);
        connectionManager.setMaxTotal(clientProperties.pooling().maxTotal());
        connectionManager.setDefaultMaxPerRoute(clientProperties.pooling().defaultMaxPerRoute());
        return connectionManager;
    }

    @Bean("clientRetryStrategy")
    public HttpRequestRetryStrategy httpRequestRetryStrategy(RestClientProperties clientProperties) {
        return new DefaultHttpRequestRetryStrategy(
                clientProperties.retry().count(),
                TimeValue.ofMilliseconds(clientProperties.retry().interval())
        );
    }

    @Bean("clientRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory(
            @Qualifier("clientConnectionManager") PoolingHttpClientConnectionManager connectionManager,
            @Qualifier("clientRetryStrategy") HttpRequestRetryStrategy retryStrategy
    ) {
        var client = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setRetryStrategy(retryStrategy)
                .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Bean("clientRestTemplate")
    public RestClient restClient(
            @Qualifier("clientRequestFactory") ClientHttpRequestFactory clientHttpRequestFactory,
            RestClientProperties clientProperties
    ) {
        return RestClient.builder()
                .requestInterceptor(new TracePropagationClientHttpRequestInterceptor())
                .requestFactory(clientHttpRequestFactory)
                .baseUrl(clientProperties.baseUrl())
                .build();
    }
}
