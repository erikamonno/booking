package com.example.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class RoomDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull private Integer roomNumber;

    private Integer bedsNumber;
    private Integer floorNumber;

    @NotNull private Double price;

    @NotNull private RoomTypeDto roomType;

    @NotNull private AccommodationFacilityDto accommodationFacility;
}
