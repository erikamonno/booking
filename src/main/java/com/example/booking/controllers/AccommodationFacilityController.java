package com.example.booking.controllers;

import com.example.booking.dto.AccommodationFacilityDto;
import com.example.booking.filters.AccommodationFacilityFilter;
import com.example.booking.services.AccommodationFacilityService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accommodation_facility")
public class AccommodationFacilityController {

    private final AccommodationFacilityService service;

    public AccommodationFacilityController(AccommodationFacilityService service) {
        this.service = service;
    }

    @PostMapping
    public AccommodationFacilityDto insertHotel(@RequestBody @Valid AccommodationFacilityDto dto) {
        return service.insertAccommodationFacility(dto);
    }

    @GetMapping(path = "{id}")
    public AccommodationFacilityDto readOneHotel(@PathVariable(name = "id") UUID id) {
        return service.readOneAccommodationFacility(id);
    }

    @GetMapping
    public Page<AccommodationFacilityDto> searchHotel(
            @Valid AccommodationFacilityFilter filter, @PageableDefault(sort = "id") Pageable pageable) {
        return service.searchAccommodationFacility(filter, pageable);
    }

    @PutMapping(path = "{id}")
    public void updateHotel(@PathVariable(name = "id") UUID id, @Valid @RequestBody AccommodationFacilityDto dto) {
        service.updateAccommodationFacility(id, dto);
    }

    @DeleteMapping(path = "{id}")
    public void deleteHotel(@PathVariable(name = "id") UUID id) {
        service.deleteAccommodationFacility(id);
    }
}
