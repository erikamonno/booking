package com.example.booking.servicies;

import com.example.booking.dto.RoomTypeDto;
import com.example.booking.filters.RoomTypeFilter;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomTypeService {

    RoomTypeDto insertType(RoomTypeDto dto);

    RoomTypeDto readOneType(UUID id);

    Page<RoomTypeDto> searchType(Pageable pageable, RoomTypeFilter filter);

    void updateType(RoomTypeDto dto, UUID id);

    void deleteType(UUID id);
}
