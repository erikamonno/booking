package com.example.booking.servicies;

import com.example.booking.dto.RoomTypeDto;
import com.example.booking.filters.RoomTypeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomTypeService {

    RoomTypeDto insertType(RoomTypeDto dto);
    RoomTypeDto readOneType(Long id);
    Page<RoomTypeDto> searchType(Pageable pageable, RoomTypeFilter filter);
    void updateType(RoomTypeDto dto, Long id);
    void deleteType(Long id);
}
