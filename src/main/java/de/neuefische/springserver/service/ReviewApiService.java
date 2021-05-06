package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReviewApiService {
    private final String reviewApiUrl = "https://jsonplaceholder.typicode.com/comments";

    private final RestTemplate restTemplate = new RestTemplate();

     public List<Review> listReviews() {
         ResponseEntity<Review[]> responseEntity = restTemplate.getForEntity(reviewApiUrl, Review[].class);
         List<Review> reviews = Arrays.asList(responseEntity.getBody());
         return reviews;
     }
}
