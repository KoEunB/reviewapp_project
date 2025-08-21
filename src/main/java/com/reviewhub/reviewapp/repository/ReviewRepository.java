package com.reviewhub.reviewapp.repository;

import com.reviewhub.reviewapp.entity.ItemEntity;
import com.reviewhub.reviewapp.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByItemId(Long id);
}
