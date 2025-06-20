package com.example.booking.servicies;

import com.example.booking.dto.AccommodationFacilityDto;
import com.example.booking.filters.AccommodationFacilityFilter;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccommodationFacilityService {

    AccommodationFacilityDto insertAccommodationFacility(AccommodationFacilityDto dto);

    AccommodationFacilityDto readOneAccommodationFacility(UUID id);

    Page<AccommodationFacilityDto> searchAccommodationFacility(AccommodationFacilityFilter filter, Pageable pageable);

    void updateAccommodationFacility(UUID id, AccommodationFacilityDto dto);

    void deleteAccommodationFacility(UUID id);
}
