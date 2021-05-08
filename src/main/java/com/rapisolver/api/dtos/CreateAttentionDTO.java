package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAttentionDTO {
    private String name;
    private Long categoryId;
}
