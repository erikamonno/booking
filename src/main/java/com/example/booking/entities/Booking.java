package com.example.booking.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_date")
    private Instant bookingDate;

    @Column(name = "date_check_in")
    private Instant dateCheckIn;

    @Column(name = "date_check_out")
    private Instant dateCheckOut;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "people_number")
    private Integer peopleNumber;
}
