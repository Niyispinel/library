package com.library.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Book extends CoreEntity{

    private long categoryId;
    private String title;
    private String ISBN;
    private String language;
    private String publisher;
    private String author;
    private LocalDateTime publisherDate;
    private Boolean isFavorite;

}