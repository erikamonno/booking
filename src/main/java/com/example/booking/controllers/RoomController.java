package com.example.booking.controllers;

import com.example.booking.dto.RoomDto;
import com.example.booking.filters.RoomFilter;
import com.example.booking.repositories.RoomRepository;
import com.example.booking.servicies.RoomService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService service) {
        this.service = service;}

    @PostMapping
    public RoomDto insertRoom (@RequestBody @Valid RoomDto dto) {
        return service.insertRoom(dto);
    }

    @GetMapping("{id}")
    public RoomDto readOneRoom(@PathVariable Long id) {
        return service.readOneRoom(id);
    }

    @GetMapping
    public Page<RoomDto> searchRoom(@PageableDefault Pageable pageable, @Valid RoomFilter filter) {
        return service.searchRoom(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody @Valid RoomDto dto) {
        service.updateRoom(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable Long id) {
        service.deleteRoom(id);
    }
}
