package com.example.booking.filters;

import java.util.UUID;
import lombok.Data;

@Data
public class RoomFilter {
    private Integer roomNumber;
    private Integer bedsNumber;
    private Integer floorNumber;
    private Double price;
    private UUID idRoomType;
    private UUID idHotel;
}
