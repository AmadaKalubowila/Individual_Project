package com.freshsip.reviewservice;





import java.util.List;

public interface ReviewService {
    void addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getAllReviews();

    ReviewDTO getReviewById(Long reviewId);

    boolean deleteReviewById(Long reviewId);

    String updateReviewById(Long reviewId, Review reviewDTO);
}
