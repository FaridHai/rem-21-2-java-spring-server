package de.neuefische.springserver.controller;

import de.neuefische.springserver.model.Review;
import de.neuefische.springserver.service.ReviewApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("review")
@RestController
public class ReviewController {
    private final ReviewApiService reviewApiService;

    @Autowired
    public ReviewController(ReviewApiService reviewApiService) {
        this.reviewApiService = reviewApiService;
    }

    @GetMapping
    public List<Review> listReviews() {
       return reviewApiService.listReviews();
    }
}
