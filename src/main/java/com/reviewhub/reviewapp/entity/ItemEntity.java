package com.reviewhub.reviewapp.entity;

import com.reviewhub.reviewapp.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "item_table")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    //책 관련 필드
    @Column
    private String author;
    @Column
    private String publisher;
    @Column
    private LocalDate publishDate;

    //영화 관련 필드
    @Column
    private String director;
    @Column
    private String actors;
    @Column
    private LocalDate releaseDate;

    //이미지
    private String imageUrl;

    public static ItemEntity toItemEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDTO.getId());
        itemEntity.setTitle(itemDTO.getTitle());
        itemEntity.setDescription(itemDTO.getDescription());
        itemEntity.setType(ItemType.valueOf(itemDTO.getType()));
        itemEntity.setAuthor(itemDTO.getAuthor());
        itemEntity.setPublisher(itemDTO.getPublisher());
        itemEntity.setPublishDate(itemDTO.getPublishDate());
        itemEntity.setDirector(itemDTO.getDirector());
        itemEntity.setActors(itemDTO.getActors());
        itemEntity.setReleaseDate(itemDTO.getReleaseDate());
        itemEntity.setImageUrl(itemDTO.getImageUrl());
        return itemEntity;
    }
}
