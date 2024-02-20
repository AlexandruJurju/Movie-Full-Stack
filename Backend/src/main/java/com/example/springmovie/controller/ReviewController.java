package com.example.springmovie.controller;

import com.example.springmovie.dto.ReviewDto;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.exception.ReviewNotFoundException;
import com.example.springmovie.exception.UserNotFoundException;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.Review;
import com.example.springmovie.model.User;
import com.example.springmovie.service.interfaces.MovieService;
import com.example.springmovie.service.interfaces.ReviewService;
import com.example.springmovie.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    // TODO: get all reviews of a movie - page
    // TODO: get average score of movie
    // TODO: get number of votes of a movie
    // TODO: get all reviews of a user - page
    @GetMapping("")
    @Operation(summary = "Get all reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a review using Id")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) throws ReviewNotFoundException {
        Review review = reviewService.findReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // TODO: dont need all information from review return, ie both user (PASSWORD) and movie
    // TODO: put in service
    @PostMapping("/movie/review")
    @Operation(summary = "Add review to movie")
    public ResponseEntity<Review> addReviewToMovie(@RequestBody ReviewDto reviewDto) throws MovieNotFoundException, UserNotFoundException {
        Review review = new Review();
        review.setText(reviewDto.getText());
        review.setScore(reviewDto.getScore());
        review.setPostedDate(LocalDate.now());

        Movie movie = movieService.findMovieById(reviewDto.getMovieId());
        User user = userService.findUserById(reviewDto.getUserId());

        review.setMovie(movie);
        review.setUser(user);

        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update review")
    public ResponseEntity<Review> updateReview(@RequestBody Review updatedReview) throws ReviewNotFoundException {
        Review updated = reviewService.updateReview(updatedReview);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a review")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
