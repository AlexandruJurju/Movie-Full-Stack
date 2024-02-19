package com.example.springmovie.controller;

import com.example.springmovie.dto.ReviewDto;
import com.example.springmovie.exception.ReviewNotFoundException;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.Review;
import com.example.springmovie.model.User;
import com.example.springmovie.service.interfaces.MovieService;
import com.example.springmovie.service.interfaces.ReviewService;
import com.example.springmovie.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // TODO: maybe dont need all information from review, ie both user (PASSWORD) and movie
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        try {
            Review review = reviewService.findReviewById(id);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO: remove useless information from return
    @PostMapping("/movie/review")
    public ResponseEntity<Review> addReviewToMovie(@RequestBody ReviewDto reviewDto) {
        Review review = new Review();
        review.setText(reviewDto.text());
        review.setScore(reviewDto.score());
        review.setPostedDate(LocalDate.now());

        Movie movie = movieService.findMovieById(reviewDto.movieId());
        User user = userService.findById(reviewDto.userId());

        review.setMovie(movie);
        review.setUser(user);

        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@RequestBody Review updatedReview) {
        try {
            Review updated = reviewService.updateReview(updatedReview);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
