package dev.vlaship.book.catalog.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class TracePropagationClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @NonNull
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            @NonNull byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {
        log.debug("TracePropagationClientHttpRequest Interceptor: modifying before sending request");
        var headers = request.getHeaders();
        headers.set("trace_id", UUID.randomUUID().toString());
        headers.set("span_id", UUID.randomUUID().toString());

        var response = execution.execute(request, body);

        log.debug("TracePropagationClientHttpRequest Interceptor: modifying after receiving response");
        return response;
    }
}
