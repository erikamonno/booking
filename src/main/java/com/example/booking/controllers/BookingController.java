package com.example.booking.controllers;

import com.example.booking.dto.BookingDto;
import com.example.booking.filters.BookingFilter;
import com.example.booking.services.BookingService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public BookingDto insertBooking(@RequestBody @Valid BookingDto dto) {
        return service.insertBooking(dto);
    }

    @GetMapping("{id}")
    public BookingDto readOneBooking(@PathVariable(name = "id") UUID id) {
        return service.readOneBooking(id);
    }

    @GetMapping
    public Page<BookingDto> searchBooking(@PageableDefault Pageable pageable, @Valid BookingFilter filter) {
        return service.searchBooking(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateBooking(@PathVariable(name = "id") UUID id, @RequestBody @Valid BookingDto dto) {
        service.updateBooking(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable(name = "id") UUID id) {
        service.deleteBooking(id);
    }
}
