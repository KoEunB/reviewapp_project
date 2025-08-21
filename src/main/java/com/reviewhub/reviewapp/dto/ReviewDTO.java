package com.reviewhub.reviewapp.dto;

import com.reviewhub.reviewapp.entity.ReviewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private Long id;
    private String contents;
    private Integer rating;
    private Long reviewerId;
    private String reviewerName; //화면에 보여줄 작성자 이름
    private Long itemId;
    private String itemName;
    private LocalDateTime createdAt;

    public static ReviewDTO toReviewDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewEntity.getId());
        reviewDTO.setContents(reviewEntity.getContents());
        reviewDTO.setRating(reviewEntity.getRating());
        reviewDTO.setReviewerId(reviewEntity.getReviewer().getId());
        reviewDTO.setReviewerName(reviewEntity.getReviewer().getUsername());
        reviewDTO.setItemId(reviewEntity.getItem().getId());
        reviewDTO.setItemName(reviewEntity.getItem().getTitle());
        reviewDTO.setCreatedAt(reviewEntity.getCreatedAt());
        return reviewDTO;
    }
}
