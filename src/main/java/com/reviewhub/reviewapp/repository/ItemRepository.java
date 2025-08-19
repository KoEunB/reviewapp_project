package com.reviewhub.reviewapp.repository;

import com.reviewhub.reviewapp.entity.ItemEntity;
import com.reviewhub.reviewapp.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByType(ItemType type);
}
