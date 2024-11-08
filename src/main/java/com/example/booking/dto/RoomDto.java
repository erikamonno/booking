package com.example.booking.dto;

import com.example.booking.entities.RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.mapstruct.Mapping;

@Data
public class RoomDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private Integer roomNumber;
    private Integer bedsNumber;
    private Integer floorNumber;
    @NotNull
    private Double price;
    @NotNull
    private Long idRoomType;
    @NotNull
    private Long idHotel;

}
