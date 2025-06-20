package com.example.booking.services;

import com.example.booking.dto.AccommodationFacilityDto;
import com.example.booking.entities.AccommodationFacility;
import com.example.booking.exceptions.AccommodationFacilityNotFoundException;
import com.example.booking.filters.AccommodationFacilityFilter;
import com.example.booking.mappers.AccommodationFacilityMapper;
import com.example.booking.repositories.AccommodationFacilityRepository;
import com.example.booking.specifications.AccommodationFacilitySpecification;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationFacilityServiceImpl implements AccommodationFacilityService {

    private final AccommodationFacilityMapper mapper;
    private final AccommodationFacilityRepository repository;

    public AccommodationFacilityServiceImpl(
            AccommodationFacilityMapper mapper, AccommodationFacilityRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public AccommodationFacilityDto insertAccommodationFacility(AccommodationFacilityDto dto) {
        AccommodationFacility entity = new AccommodationFacility();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setMail(dto.getMail());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public AccommodationFacilityDto readOneAccommodationFacility(UUID id) {
        var oEntity = repository.findById(id);
        if (oEntity.isEmpty()) {
            throw new AccommodationFacilityNotFoundException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
        AccommodationFacility entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<AccommodationFacilityDto> searchAccommodationFacility(
            AccommodationFacilityFilter filter, Pageable pageable) {
        return repository
                .findAll(new AccommodationFacilitySpecification(filter), pageable)
                .map(hotel -> mapper.toDto(hotel));
    }

    @Transactional
    @Override
    public void updateAccommodationFacility(UUID id, AccommodationFacilityDto dto) {
        var oEntity = repository.findById(id);
        if (oEntity.isEmpty()) {
            throw new AccommodationFacilityNotFoundException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setMail(dto.getMail());
    }

    @Override
    public void deleteAccommodationFacility(UUID id) {
        repository.deleteById(id);
    }
}
