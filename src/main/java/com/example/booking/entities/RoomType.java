package com.example.booking.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "increase_percentage")
    private Integer increasePercentage;
}
