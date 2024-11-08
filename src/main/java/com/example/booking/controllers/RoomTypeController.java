package com.example.booking.controllers;

import com.example.booking.dto.RoomTypeDto;
import com.example.booking.filters.RoomTypeFilter;
import com.example.booking.servicies.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room-type")
public class RoomTypeController {
    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service) {
        this.service = service;
    }

    @PostMapping
    public RoomTypeDto insertType(@RequestBody RoomTypeDto dto) {
        return service.insertType(dto);
    }

    @GetMapping("{id}")
    public RoomTypeDto readOneType(@PathVariable Long id) {
        return service.readOneType(id);
    }

    @GetMapping
    public Page<RoomTypeDto> searchType(@PageableDefault Pageable pageable, @Valid RoomTypeFilter filter) {
        return service.searchType(pageable, filter);
    }

    @PutMapping("{id}")
    void updateType(@RequestBody RoomTypeDto dto, @PathVariable Long id) {
        service.updateType(dto, id);
    }

    @DeleteMapping("{id}")
    void deleteType(@PathVariable Long id) {
        service.deleteType(id);
    }
}
