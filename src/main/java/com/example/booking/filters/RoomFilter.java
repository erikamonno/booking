package com.example.booking.filters;

import lombok.Data;

@Data
public class RoomFilter {
    private Integer roomNumber;
    private Integer bedsNumber;
    private Integer floorNumber;
    private Double price;
    private Long idRoomType;
    private Long idHotel;
}
