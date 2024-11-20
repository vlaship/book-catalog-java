package dev.vlaship.book.catalog.client;

import dev.vlaship.book.catalog.api.BookApi;
import dev.vlaship.book.catalog.client.config.BookFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "book-catalog",
    url = "${client.rest-client.baseUrl}",
    configuration = BookFeignClientConfig.class
)
public interface BookClient extends BookApi {
}
