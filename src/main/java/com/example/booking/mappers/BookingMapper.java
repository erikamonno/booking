package com.example.booking.mappers;

import com.example.booking.dto.BookingDto;
import com.example.booking.entities.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDto toDto(Booking entity);
}
