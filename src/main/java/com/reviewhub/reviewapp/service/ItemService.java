package com.reviewhub.reviewapp.service;

import com.reviewhub.reviewapp.dto.ItemDTO;
import com.reviewhub.reviewapp.entity.ItemEntity;
import com.reviewhub.reviewapp.entity.ItemType;
import com.reviewhub.reviewapp.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemDTO> findByType(String type) {
        List<ItemEntity> itemEntityList = itemRepository.findByType(ItemType.valueOf(type));
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntityList) {
            itemDTOList.add(ItemDTO.toItemDTO(itemEntity));
        }
        return itemDTOList;
    }

    public ItemDTO findById(Long id) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(id);
        if (optionalItemEntity.isPresent()) {
            return ItemDTO.toItemDTO(optionalItemEntity.get());
        } else {
            return null;
        }
    }
}
