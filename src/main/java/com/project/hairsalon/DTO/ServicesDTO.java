package com.project.hairsalon.DTO;

import com.project.hairsalon.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data @NoArgsConstructor @AllArgsConstructor
public class ServicesDTO {
    private Long id;

    private String name;

    private Double price;

    private String description;

    private Long categoryId;
}
