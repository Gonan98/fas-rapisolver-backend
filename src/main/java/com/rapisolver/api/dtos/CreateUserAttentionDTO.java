package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAttentionDTO {
    private double price;
    private String detail;
    private Long userId;
    private Long attentionId;
}
