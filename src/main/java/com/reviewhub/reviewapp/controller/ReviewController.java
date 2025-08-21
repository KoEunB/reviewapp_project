package com.reviewhub.reviewapp.controller;

import com.reviewhub.reviewapp.dto.ReviewDTO;
import com.reviewhub.reviewapp.entity.ItemEntity;
import com.reviewhub.reviewapp.repository.ItemRepository;
import com.reviewhub.reviewapp.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ItemRepository itemRepository;

    @GetMapping("/write/{itemId}")
    public String saveForm(@PathVariable Long itemId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        //로그인 되어 있는지 확인
        Long memberId = (Long) session.getAttribute("loginId");
        if (memberId == null) {
            redirectAttributes.addFlashAttribute("error", "리뷰 작성은 로그인 후 이용할 수 있습니다.");
            return "redirect:/member/login";
        }

        //DB에서 해당 아이템 조회
        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("아이템 없음"));

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setItemId(item.getId());
        reviewDTO.setItemName(item.getTitle());

        model.addAttribute("reviewDTO", reviewDTO);
        return "reviewsave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ReviewDTO reviewDTO, Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        if (memberId == null) {
            return "redirect:/member/login";
        }
        reviewDTO.setReviewerId(memberId);
        reviewService.save(reviewDTO);

        return "redirect:/item/id/" + reviewDTO.getItemId();
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");

        //1. 리뷰 정보 조회
        ReviewDTO reviewDTO = reviewService.findById(id);

        //2. 권한 확인 (로그인 유저와 리뷰 작성자가 동일한지)
        if (memberId == null || !memberId.equals(reviewDTO.getReviewerId())) {
            return "redirect:/item/id/" + reviewDTO.getItemId();
        }

        model.addAttribute("reviewDTO", reviewDTO);
        return "reviewupdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ReviewDTO reviewDTO, Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");

        // 1. 권한 확인
        if (memberId == null || !memberId.equals(reviewDTO.getReviewerId())) {
            return "redirect:/item/id/" + reviewDTO.getItemId();
        }

        // 2. 수정 처리
        reviewService.update(reviewDTO);

        return "redirect:/item/id/" + reviewDTO.getItemId();
    }
}
