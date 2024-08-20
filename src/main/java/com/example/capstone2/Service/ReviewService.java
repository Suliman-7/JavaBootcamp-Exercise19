package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Review;
import com.example.capstone2.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public void deleteReview(int id){
        Review r = reviewRepository.findById(id);
        if(r == null){
            throw new ApiException("Review not found");
        }
        reviewRepository.delete(r);
    }

}
