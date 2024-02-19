package com.example.springmovie.service;

import com.example.springmovie.exception.ReviewNotFoundException;
import com.example.springmovie.model.Review;
import com.example.springmovie.repositories.ReviewRepository;
import com.example.springmovie.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findReviewById(Long id) throws ReviewNotFoundException {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) throws ReviewNotFoundException {
        reviewRepository.findById(review.getId())
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id " + review.getId()));

        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

}
