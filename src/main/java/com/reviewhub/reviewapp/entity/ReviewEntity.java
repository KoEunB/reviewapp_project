package com.reviewhub.reviewapp.entity;

import com.reviewhub.reviewapp.dto.ReviewDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "review_table")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String contents;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public static ReviewEntity toReviewEntity(ReviewDTO reviewDTO, MemberEntity member, ItemEntity item) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(reviewDTO.getId());
        reviewEntity.setContents(reviewDTO.getContents());
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setReviewer(member);
        reviewEntity.setItem(item);
        return reviewEntity;
    }

    public static ReviewEntity toUpdateReviewEntity(ReviewDTO reviewDTO, MemberEntity member, ItemEntity item) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(reviewDTO.getId());
        reviewEntity.setContents(reviewDTO.getContents());
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setReviewer(member);
        reviewEntity.setItem(item);
        return reviewEntity;
    }
}
