package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.model.Review;
import de.neuefische.springserver.model.ReviewApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ReviewApiService {
    private final String reviewApiUrl = "https://jsonplaceholder.typicode.com/comments";

    private final RestTemplate restTemplate;
    private final ProductService productService;

    @Autowired
    public ReviewApiService(ProductService productService, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.productService = productService;
    }

    public List<ReviewApi> listApiReviews() {
        ResponseEntity<ReviewApi[]> responseEntity = restTemplate.getForEntity(reviewApiUrl, ReviewApi[].class);
        if (responseEntity.getBody() != null) {
            List<ReviewApi> reviews = Arrays.asList(responseEntity.getBody());
            return reviews;
        }
        return List.of();
    }

    public List<Review> listReviews() {
        List<Review> reviews = new ArrayList<>();
        for (ReviewApi reviewApi : listApiReviews()) {
            Review review = new Review();
            review.setId(reviewApi.getId());
            review.setContent(reviewApi.getBody());
            review.setEmail(reviewApi.getEmail());
            review.setTitle(reviewApi.getName());
            Optional<Product> productOptional = productService.getProductById(reviewApi.getPostId());
            if (productOptional.isPresent()) {
                review.setProduct(productOptional.get());
                reviews.add(review);
            }
        }
        return reviews;
    }
}
