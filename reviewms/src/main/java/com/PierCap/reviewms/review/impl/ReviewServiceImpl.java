package com.PierCap.reviewms.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PierCap.reviewms.review.Review;
import com.PierCap.reviewms.review.ReviewRepository;
import com.PierCap.reviewms.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }



    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.getByCompanyId(companyId);
        return reviews;
    }



    @Override
    public boolean createReview(Long companyId, Review review) {
        if (companyId != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;

    }



    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }



    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }



    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
    
}
