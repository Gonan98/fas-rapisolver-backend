package com.rapisolver.api.dtos;

import com.rapisolver.api.entities.Customer;
import com.rapisolver.api.entities.Location;
import com.rapisolver.api.entities.SupplierAttentions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class CreateReservationDTO {

    private String country;

    private String state;

    private String city;

    private String address;

    private Long userId;

    private Long supplierAttentionId;

    private Date dateRequested;

    private String comment;
}
