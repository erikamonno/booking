package com.example.booking.servicies;

import com.example.booking.dto.HotelDto;
import com.example.booking.filters.HotelFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

    HotelDto insertHotel(HotelDto dto);
    HotelDto readOneHotel(Long id);
    Page<HotelDto> searchHotel(HotelFilter filter, Pageable pageable);
    void updateHotel(Long id, HotelDto dto);
    void deleteHotel(Long id);
}
