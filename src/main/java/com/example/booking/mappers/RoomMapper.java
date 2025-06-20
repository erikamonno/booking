package com.example.booking.mappers;

import com.example.booking.dto.RoomDto;
import com.example.booking.entities.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDto toDto(Room entity);
}
