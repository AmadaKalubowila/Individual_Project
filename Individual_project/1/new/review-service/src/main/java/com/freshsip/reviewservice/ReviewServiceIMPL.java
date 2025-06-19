package com.freshsip.reviewservice;




import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceIMPL implements ReviewService {
    @Autowired
    private RestTemplate restTemplate;

    public UserDTO getUserByEmail(String email) {
        return restTemplate.getForObject("http://user-service:8081/FreshSip/adminUser/" + email, UserDTO.class);


    }

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addReview(ReviewDTO reviewDTO) {
        String email = reviewDTO.getEmail();
        UserDTO user = getUserByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Review review = modelMapper.map(reviewDTO, Review.class);
        // ✅ store email
        review.setUser(user);             // ✅ set transient if needed for runtime
        review.setEmail(email);
        reviewRepository.save(review);
    }


    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAllReviewsWithUser();
        return reviews.stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setReview(review.getReview());
            reviewDTO.setEmail(review.getEmail());
            reviewDTO.setReviewId(review.getReviewId());
            // Get email from associated User
            return reviewDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(Long reviewId){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        return modelMapper.map(reviewOptional, ReviewDTO.class);
    }

    @Override
    public boolean deleteReviewById(Long reviewId){
        reviewRepository.deleteById(reviewId);
        return true;
    }

    @Override
    public String updateReviewById(Long reviewId, Review updateReview){
        ReviewDTO reviewDTO = new ReviewDTO();

        Optional<Review>reviewOptional = reviewRepository.findById(reviewId);

        Review existingReview = reviewOptional.get();

        existingReview.setReview(updateReview.getReview());
        Review saveReview = reviewRepository.save(existingReview);

        return ("Update Successful");

    }


}

