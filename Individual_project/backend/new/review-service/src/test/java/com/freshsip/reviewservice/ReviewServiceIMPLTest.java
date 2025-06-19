//package com.freshsip.reviewservice;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.client.RestTemplate;
//
//public class ReviewServiceIMPLTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private ReviewServiceIMPL reviewServiceIMPL;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddReviewWithValidUser() {
//        ReviewDTO reviewDTO = new ReviewDTO();
//        reviewDTO.setEmail("test@example.com");
//        reviewDTO.setReview("Great service!");
//
//        // Simulated REST response
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("test@example.com");
//
//        Review review = new Review();
//        review.setReview("Great service!");
//
//        // Mocks
//        Mockito.when(restTemplate.getForObject("http://localhost:8081/users/email/test@example.com", UserDTO.class))
//                .thenReturn(userDTO);
//
//        Mockito.when(modelMapper.map(reviewDTO, Review.class)).thenReturn(review);
//
//        // Call method
//        reviewServiceIMPL.addReview(reviewDTO);
//
//        // Verify
//        Mockito.verify(reviewRepository).save(review);
//    }
//}
