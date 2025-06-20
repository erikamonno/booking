package com.example.booking.mappers;

import com.example.booking.dto.AccommodationFacilityDto;
import com.example.booking.entities.AccommodationFacility;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccommodationFacilityMapper {

    AccommodationFacilityDto toDto(AccommodationFacility entity);
}
