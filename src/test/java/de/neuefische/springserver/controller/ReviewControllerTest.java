package de.neuefische.springserver.controller;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.model.Review;
import de.neuefische.springserver.model.ReviewApi;
import de.neuefische.springserver.repository.ProductRepository;
import de.neuefische.springserver.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    RestTemplate mockTemplate;

    @Autowired
    private ProductService productService;

    @LocalServerPort
    private int port;

    @Test
    void listReviews() {
        //GIVEN
        ReviewApi[] reviewApis = new ReviewApi[]{
                new ReviewApi("1", "1 Review", "1", "email@email.com", "Produkt ist kacke"),
                new ReviewApi("1", "2. Review", "2", "email@email.com", "Produkt ist super duper mega toll")
        };
        when(mockTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments", ReviewApi[].class)).thenReturn(ResponseEntity.ok(reviewApis));

        Product p = new Product("apple", "1");
        productService.addProduct(p);
        Review review1 = new Review();
        review1.setId("1");
        review1.setTitle("1 Review");
        review1.setProduct(p);
        review1.setEmail("email@email.com");
        review1.setContent("Produkt ist kacke");
        Review review2 = new Review();
        review2.setId("2");
        review2.setTitle("2. Review");
        review2.setProduct(p);
        review2.setEmail("email@email.com");
        review2.setContent("Produkt ist super duper mega toll");

        //WHEN
        ResponseEntity<Review[]> response = restTemplate.getForEntity("http://localhost:" + port + "/review", Review[].class);
        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());

        assertThat(Arrays.asList(response.getBody()), containsInAnyOrder(review1, review2));
    }
}