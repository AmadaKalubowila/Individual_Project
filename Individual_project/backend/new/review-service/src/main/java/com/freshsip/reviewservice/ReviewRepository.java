package com.freshsip.reviewservice;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r")
    List<Review> findAllReviewsWithUser();

    @Query("SELECT r FROM Review r WHERE r.reviewId = ?1")
    Optional<Review> findById(Long reviewId);

    void deleteById(Long reviewId);
}
