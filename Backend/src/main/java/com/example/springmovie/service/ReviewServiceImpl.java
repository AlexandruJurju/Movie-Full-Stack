package com.example.springmovie.service;

import com.example.springmovie.dto.ReviewDto;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.exception.ReviewNotFoundException;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.Review;
import com.example.springmovie.model.User;
import com.example.springmovie.repositories.ReviewRepository;
import com.example.springmovie.service.interfaces.MovieService;
import com.example.springmovie.service.interfaces.ReviewService;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final UserService userService;

    @Override
    public Review createReview(ReviewDto reviewDto) throws MovieNotFoundException, UserNotFoundException {
        Review review = new Review();
        review.setText(reviewDto.text());
        review.setScore(reviewDto.score());
        review.setPostedDate(LocalDate.now());

        Movie movie = movieService.findMovieById(reviewDto.movieId());
        User user = userService.findUserById(reviewDto.userId());

        review.setMovie(movie);
        review.setUser(user);

        return reviewRepository.save(review);
    }

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
