package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_attentions")
@Data
public class UserAttention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double price;

    @Column(length = 100)
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attention_id", nullable = false)
    private Attention attention;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAttention")
    private List<Reservation> reservations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAttention")
    private List<Score> scores;
}
