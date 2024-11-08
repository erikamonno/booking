package com.example.booking.mappers;

import com.example.booking.dto.BookingDto;
import com.example.booking.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "idRoom", source = "room.id")
    @Mapping(target = "idCustomer", source = "customer.id")
    BookingDto toDto(Booking entity);
    Booking toEntity(BookingDto dto);

}
