package com.example.booking.controllers;

import com.example.booking.dto.HotelDto;
import com.example.booking.filters.HotelFilter;
import com.example.booking.servicies.HotelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hotel")
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping
    public HotelDto insertHotel(@RequestBody @Valid HotelDto dto) {
        return service.insertHotel(dto);
    }

    @GetMapping(path = "{id}")
    public HotelDto readOneHotel(@PathVariable Long id) {
        return service.readOneHotel(id);
    }

    @GetMapping
    public Page<HotelDto> searchHotel(@Valid HotelFilter filter, @PageableDefault(sort = "id") Pageable pageable) {
        return service.searchHotel(filter, pageable);
    }

    @PutMapping(path = "{id}")
    public void updateHotel(@PathVariable Long id,@Valid @RequestBody HotelDto dto) {
        service.updateHotel(id, dto);
    }

    @DeleteMapping(path = "{id}")
    public void deleteHotel(@PathVariable Long id) {
        service.deleteHotel(id);
    }
}
