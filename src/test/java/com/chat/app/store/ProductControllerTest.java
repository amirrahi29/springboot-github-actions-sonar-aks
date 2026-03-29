package com.chat.app.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Mock
	private ProductService productService;

	private ProductController controller;

	@BeforeEach
	void setUp() {
		controller = new ProductController(productService);
	}

	@Test
	void listReturnsClientPayload() {
		when(productService.getProducts()).thenReturn("[{\"id\":1}]");
		assertThat(controller.list()).isEqualTo("[{\"id\":1}]");
		verify(productService).getProducts();
	}

	@Test
	void getReturnsSingleProduct() {
		when(productService.getProduct(2L)).thenReturn("{\"id\":2}");
		assertThat(controller.get(2L)).isEqualTo("{\"id\":2}");
		verify(productService).getProduct(2L);
	}

	@Test
	void createForwardsBody() {
		String body = "{\"title\":\"x\"}";
		when(productService.createProduct(body)).thenReturn("{\"id\":9}");
		assertThat(controller.create(body)).isEqualTo("{\"id\":9}");
		verify(productService).createProduct(body);
	}

	@Test
	void updateForwardsIdAndBody() {
		String body = "{\"title\":\"y\"}";
		when(productService.updateProduct(3L, body)).thenReturn("{\"id\":3}");
		assertThat(controller.update(3L, body)).isEqualTo("{\"id\":3}");
		verify(productService).updateProduct(3L, body);
	}

	@Test
	void deleteForwardsId() {
		when(productService.deleteProduct(4L)).thenReturn("{\"id\":4}");
		assertThat(controller.delete(4L)).isEqualTo("{\"id\":4}");
		verify(productService).deleteProduct(4L);
	}
}
