package com.scaler.backendcapstoneproject.clients;

import com.scaler.backendcapstoneproject.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {

    private final RestClient restClient = RestClient.create("https://fakestoreapi.com");

    public FakeStoreProductDto getProductById(Long id) {
        // FakeStore returns 200 with an EMPTY body for unknown ids -> body() gives null
        return restClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .body(FakeStoreProductDto.class);
    }

    public List<FakeStoreProductDto> getAllProducts() {
        FakeStoreProductDto[] response = restClient.get()
                .uri("/products")
                .retrieve()
                .body(FakeStoreProductDto[].class);
        return response == null ? List.of() : Arrays.asList(response);
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto request) {
        return restClient.post()
                .uri("/products")
                .body(request)
                .retrieve()
                .body(FakeStoreProductDto.class);
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto request) {
        return restClient.put()
                .uri("/products/{id}", id)
                .body(request)
                .retrieve()
                .body(FakeStoreProductDto.class);
    }

    public void deleteProduct(Long id) {
        restClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}