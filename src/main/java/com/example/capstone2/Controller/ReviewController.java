package com.example.capstone2.Controller;

import com.example.capstone2.Model.Review;
import com.example.capstone2.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor

public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity getReviews() {
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
       return ResponseEntity.status(200).body("review deleted");
    }


}
