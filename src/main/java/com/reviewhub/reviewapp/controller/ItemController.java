package com.reviewhub.reviewapp.controller;

import com.reviewhub.reviewapp.dto.ItemDTO;
import com.reviewhub.reviewapp.service.ItemService;
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

    @GetMapping("/item/type/{type}")
    public String getItemByType(@PathVariable String type, Model model) {
        List<ItemDTO> itemDTOList = itemService.findByType(type.toUpperCase());
        model.addAttribute("itemsList", itemDTOList);
        model.addAttribute("selectedType", type);
        return "index";
    }

    @GetMapping("/item/id/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item", itemDTO);
        return "itemdetail";
    }
}
