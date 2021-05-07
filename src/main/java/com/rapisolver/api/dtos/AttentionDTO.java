package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttentionDTO {
    private Long id;
    private String name;
    private CategoryDTO category;
}
