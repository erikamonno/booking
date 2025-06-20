package com.example.booking.controllers;

import com.example.booking.dto.RoomDto;
import com.example.booking.filters.RoomFilter;
import com.example.booking.servicies.RoomService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room")
public class RoomController {
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public RoomDto insertRoom(@RequestBody @Valid RoomDto dto) {
        return service.insertRoom(dto);
    }

    @GetMapping("{id}")
    public RoomDto readOneRoom(@PathVariable(name = "id") UUID id) {
        return service.readOneRoom(id);
    }

    @GetMapping
    public Page<RoomDto> searchRoom(@PageableDefault Pageable pageable, @Valid RoomFilter filter) {
        return service.searchRoom(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateRoom(@PathVariable(name = "id") UUID id, @RequestBody @Valid RoomDto dto) {
        service.updateRoom(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable(name = "id") UUID id) {
        service.deleteRoom(id);
    }
}
