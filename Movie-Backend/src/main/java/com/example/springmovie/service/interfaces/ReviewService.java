package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.ReviewDto;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.exception.ReviewNotFoundException;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewDto reviewDto) throws MovieNotFoundException, UserNotFoundException;

    List<Review> findAllReviews();

    Review findReviewById(Long id) throws ReviewNotFoundException;

    Review updateReview(Review review) throws ReviewNotFoundException;

    void deleteReview(Long id);
}
