package de.neuefische.springserver.controller;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.model.Review;
import de.neuefische.springserver.model.ReviewApi;
import de.neuefische.springserver.repository.ProductRepository;
import de.neuefische.springserver.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private ProductService productService;

    @LocalServerPort
    private int port;

    @Test
    void listReviews() {
        //GIVEN
        Product p = new Product("apple", "1");
        productService.addProduct(p);
        Review review1 = new Review();
        review1.setId("1");
        Review review2 = new Review();
        review2.setId("2");
        Review review3 = new Review();
        review3.setId("3");
        Review review4 = new Review();
        review4.setId("4");
        Review review5 = new Review();
        review5.setId("5");

        //WHEN
        ResponseEntity<Review[]> response = restTemplate.getForEntity("http://localhost:" + port + "/review", Review[].class);
        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());

        assertThat(Arrays.asList(response.getBody()), containsInAnyOrder(review1, review2, review3, review5, review4));
    }
}