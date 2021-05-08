package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationDTO {
    private Date datetime;
    private String address;
    private Integer status;
    private Date createdAt;
    private LocationDTO location;
    private UserAttentionDTO userAttention;
    private UserDTO user;
}
