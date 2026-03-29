package com.chat.app.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductService {

	private final RestClient client;

	public ProductService(@Value("${fakestore.base-url}") String baseUrl) {
		this.client = RestClient.builder().baseUrl(baseUrl).build();
	}

	public String getProducts() {
		return client.get().uri("/products").retrieve().body(String.class);
	}

	public String getProduct(long id) {
		return client.get().uri("/products/{id}", id).retrieve().body(String.class);
	}

	public String createProduct(String jsonBody) {
		return client.post()
				.uri("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.body(jsonBody)
				.retrieve()
				.body(String.class);
	}

	public String updateProduct(long id, String jsonBody) {
		return client.put()
				.uri("/products/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(jsonBody)
				.retrieve()
				.body(String.class);
	}

	public String deleteProduct(long id) {
		return client.delete().uri("/products/{id}", id).retrieve().body(String.class);
	}
}
