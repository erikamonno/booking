package com.example.booking.mappers;

import com.example.booking.dto.RoomTypeDto;
import com.example.booking.entities.RoomType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    RoomTypeDto toDto(RoomType entity);
    RoomType toEntity(RoomTypeDto dto);

}
