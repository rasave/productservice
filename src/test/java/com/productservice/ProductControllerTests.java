package com.productservice;

import com.productservice.controller.ApprovalController;
import com.productservice.controller.ProductController;
import com.productservice.entity.Approval;
import com.productservice.entity.Product;
import com.productservice.service.ApprovalService;
import com.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ProductControllerTests {

	@Mock
	ProductService productService;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	public void init() {
		openMocks(this);
	}

	@Test
	void testGetAllProduct() {
		List<Product> approvals = getProduct();
		when(productService.getAllProducts()).thenReturn(approvals);
		List<Product> productList = productController.getAllProducts().getBody();
		assertEquals(approvals.size(), productList.size());
	}

	@Test
	void testSpecifiedProduct() {
		List<Product> approvals = getProduct();
		Date d = new Date();
		when(productService.getSpecificProducts("", 0d,0d,d,d)).thenReturn(approvals);
		Iterable<Product> productList = productController.getProducts(Optional.of(""),Optional.of(0d),Optional.of(0d),Optional.of(d),Optional.of(d)).getBody();
		assertEquals(approvals.get(0), productList.iterator().next());
	}

	private List<Product> getProduct(){
		Product a1 = new Product();
		a1.setId(1);
		List<Product> products = Arrays.asList(a1);
		return products;
	}

}
