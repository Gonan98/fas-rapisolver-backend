package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
