package com.example.springmovie.service.interfaces;

import com.example.springmovie.exception.ReviewNotFoundException;
import com.example.springmovie.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews();

    Review findReviewById(Long id) throws ReviewNotFoundException;

    Review createReview(Review review);

    Review updateReview(Review review) throws ReviewNotFoundException;

    void deleteReview(Long id);
}
