package com.library.api.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category extends CoreEntity {

    private String name;
    private String referenceNo;
    private String status;
}
