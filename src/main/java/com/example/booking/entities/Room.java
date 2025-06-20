package com.example.booking.entities;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "beds_number")
    private Integer bedsNumber;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_room_type")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private AccommodationFacility accommodationFacility;
}
