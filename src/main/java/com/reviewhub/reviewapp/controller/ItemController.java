package com.reviewhub.reviewapp.controller;

import com.reviewhub.reviewapp.dto.ItemDTO;
import com.reviewhub.reviewapp.entity.ReviewEntity;
import com.reviewhub.reviewapp.service.ItemService;
import com.reviewhub.reviewapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/item/type/{type}")
    public String getItemByType(@PathVariable String type, Model model) {
        List<ItemDTO> itemDTOList = itemService.findByType(type.toUpperCase());
        model.addAttribute("itemsList", itemDTOList);
        model.addAttribute("selectedType", type);
        return "index";
    }

    @GetMapping("/item/id/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        //아이템 정보 조회
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item", itemDTO);

        //리뷰 목록 조회
        List<ReviewEntity> ListReviewEntity = reviewService.findByItemId(id);
        model.addAttribute("reviewList", ListReviewEntity);

        //평점 계산
        double averageRating = 0.0;
        if (!ListReviewEntity.isEmpty()) {
            averageRating = ListReviewEntity.stream()
                    .mapToInt(ReviewEntity::getRating)
                    .average()
                    .orElse(0.0);
        }
        model.addAttribute("averageRating", String.format("%.1f", averageRating));

        return "itemdetail";
    }
}
