package com.example.booking.mappers;

import com.example.booking.dto.RoomDto;
import com.example.booking.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "idHotel", source = "hotel.id")
    @Mapping(target = "idRoomType", source = "roomType.id")
    RoomDto toDto(Room entity);
    Room toEntity(RoomDto dto);
}
