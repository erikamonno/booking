package com.example.booking.servicies;

import com.example.booking.dto.RoomDto;
import com.example.booking.filters.RoomFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomDto insertRoom(RoomDto dto);
    RoomDto readOneRoom(Long id);
    Page<RoomDto> searchRoom(Pageable pageable, RoomFilter filter);
    void updateRoom(Long id, RoomDto dto);
    void deleteRoom(Long id);
}
