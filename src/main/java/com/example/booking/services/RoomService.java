package com.example.booking.services;

import com.example.booking.dto.RoomDto;
import com.example.booking.filters.RoomFilter;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomDto insertRoom(RoomDto dto);

    RoomDto readOneRoom(UUID id);

    Page<RoomDto> searchRoom(Pageable pageable, RoomFilter filter);

    void updateRoom(UUID id, RoomDto dto);

    void deleteRoom(UUID id);
}
