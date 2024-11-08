package com.example.booking.mappers;

import com.example.booking.dto.HotelDto;
import com.example.booking.entities.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDto toDto(Hotel entity);
    Hotel toEntity(HotelDto dto);
}
