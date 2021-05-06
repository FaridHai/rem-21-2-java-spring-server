package de.neuefische.springserver.controller;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private ProductService productService;

    @LocalServerPort
    private int port;

    @Test
    void getProductList() {

    }

    @Test
    void addProduct() {
        //GIVEN
        Product newProduct = new Product("apple", "1");

        //WHEN
        ResponseEntity<Product> response = restTemplate.postForEntity("http://localhost:" + port + "/product", newProduct, Product.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
        assertThat(response.getBody(), equalTo(newProduct));
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getProductById() {
    }
}