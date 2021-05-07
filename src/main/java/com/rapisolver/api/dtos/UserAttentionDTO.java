package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAttentionDTO {
    private Long id;
    private double price;
    private String detail;
    private UserDTO user;
    private AttentionDTO attention;
}
