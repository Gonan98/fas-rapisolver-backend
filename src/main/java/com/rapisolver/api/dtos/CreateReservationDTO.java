package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateReservationDTO {
    private Date datetime;
    private String address;
    private Long locationId;
    private Long userAttentionId;
    private Long userId;
}
