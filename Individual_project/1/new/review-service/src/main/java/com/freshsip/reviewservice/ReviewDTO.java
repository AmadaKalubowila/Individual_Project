package com.freshsip.reviewservice;



import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReviewDTO {
    private Long reviewId;
    private String email;
    private String review;
}

