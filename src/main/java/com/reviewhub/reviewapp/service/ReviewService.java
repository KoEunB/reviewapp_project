package com.reviewhub.reviewapp.service;

import com.reviewhub.reviewapp.dto.ReviewDTO;
import com.reviewhub.reviewapp.entity.ItemEntity;
import com.reviewhub.reviewapp.entity.MemberEntity;
import com.reviewhub.reviewapp.entity.ReviewEntity;
import com.reviewhub.reviewapp.repository.ItemRepository;
import com.reviewhub.reviewapp.repository.MemberRepository;
import com.reviewhub.reviewapp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public void save(ReviewDTO reviewDTO) {
        // 1. 로그인한 회원 엔티티 조회
        MemberEntity member = memberRepository.findById(reviewDTO.getReviewerId())
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        // 2. 아이템 엔티티 조회
        ItemEntity item = itemRepository.findById(reviewDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("아이템 없음"));

        ReviewEntity reviewEntity = ReviewEntity.toReviewEntity(reviewDTO, member, item);
        reviewRepository.save(reviewEntity);
    }

    public List<ReviewEntity> findByItemId(Long itemId) {
        return reviewRepository.findByItemId(itemId);
    }

    public ReviewDTO findById(Long id) {
        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findById(id);
        if (optionalReviewEntity.isPresent()) {
            return ReviewDTO.toReviewDTO(optionalReviewEntity.get());
        } else {
            return null;
        }
    }

    public void update(ReviewDTO reviewDTO) {
        // 1. 로그인한 회원 엔티티 조회
        MemberEntity member = memberRepository.findById(reviewDTO.getReviewerId())
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        // 2. 아이템 엔티티 조회
        ItemEntity item = itemRepository.findById(reviewDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("아이템 없음"));

        reviewRepository.save(ReviewEntity.toUpdateReviewEntity(reviewDTO, member, item));
    }

    public List<ReviewEntity> findByReviewerId(Long reviewerId) {
        return reviewRepository.findByReviewerId(reviewerId);
    }
}
