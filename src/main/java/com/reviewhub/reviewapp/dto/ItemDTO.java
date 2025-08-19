package com.reviewhub.reviewapp.dto;

import com.reviewhub.reviewapp.entity.ItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemDTO {
    private Long id;
    private String title;
    private String description;
    private String type; //BOOK 또는 MOVIE

    //책일 경우
    private String author;
    private String publisher;
    private LocalDate publishDate;

    //영화일 경우
    private String director;
    private String actors;
    private LocalDate releaseDate;

    //이미지
    private String imageUrl;

    public static ItemDTO toItemDTO(ItemEntity itemEntity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getId());
        itemDTO.setTitle(itemEntity.getTitle());
        itemDTO.setDescription(itemEntity.getDescription());
        itemDTO.setType(itemEntity.getType().name());
        itemDTO.setAuthor(itemEntity.getAuthor());
        itemDTO.setPublisher(itemEntity.getPublisher());
        itemDTO.setPublishDate(itemEntity.getPublishDate());
        itemDTO.setDirector(itemEntity.getDirector());
        itemDTO.setActors(itemEntity.getActors());
        itemDTO.setReleaseDate(itemEntity.getReleaseDate());
        itemDTO.setImageUrl(itemEntity.getImageUrl());
        return itemDTO;
    }
}
