package dev.vlaship.book.catalog.client;

import dev.vlaship.book.catalog.api.AuthorApi;
import dev.vlaship.book.catalog.client.config.AuthorFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "author-catalog",
    url = "${client.rest-client.baseUrl}",
    configuration = AuthorFeignClientConfig.class
)
public interface AuthorClient extends AuthorApi {
}
