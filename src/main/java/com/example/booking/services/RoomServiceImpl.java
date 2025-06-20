package com.example.booking.services;

import com.example.booking.dto.RoomDto;
import com.example.booking.entities.Room;
import com.example.booking.exceptions.RoomNotFoundException;
import com.example.booking.filters.RoomFilter;
import com.example.booking.mappers.ReferenceMapper;
import com.example.booking.mappers.RoomMapper;
import com.example.booking.repositories.RoomRepository;
import com.example.booking.specifications.RoomSpecification;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomMapper mapper;
    private final RoomRepository repository;
    private final RoomTypeService roomTypeService;
    private final AccommodationFacilityService accommodationFacilityService;
    private final ReferenceMapper referenceMapper;

    public RoomServiceImpl(
            RoomMapper mapper,
            RoomRepository repository,
            RoomTypeService roomTypeService,
            AccommodationFacilityService accommodationFacilityService,
            ReferenceMapper referenceMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.roomTypeService = roomTypeService;
        this.accommodationFacilityService = accommodationFacilityService;
        this.referenceMapper = referenceMapper;
    }

    @Override
    public RoomDto insertRoom(RoomDto dto) {
        log.info("RoomService.insertType() invoked with parameters {}", dto);
        var roomType = roomTypeService.readOneType(dto.getRoomType().getId());
        var accommodationFacility = accommodationFacilityService.readOneAccommodationFacility(
                dto.getAccommodationFacility().getId());
        Room entity = new Room();
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setBedsNumber(dto.getBedsNumber());
        entity.setFloorNumber(dto.getFloorNumber());
        entity.setPrice(dto.getPrice());
        entity.setRoomType(referenceMapper.toRoomType(roomType.getId()));
        entity.setAccommodationFacility(referenceMapper.toAccommodationFacility(accommodationFacility.getId()));
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public RoomDto readOneRoom(UUID id) {
        var oEntity = repository.findById(id);
        if (oEntity.isEmpty()) {
            throw new RoomNotFoundException(HttpStatus.NOT_FOUND, "Room not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<RoomDto> searchRoom(Pageable pageable, RoomFilter filter) {
        return repository.findAll(new RoomSpecification(filter), pageable).map(room -> mapper.toDto(room));
    }

    @Override
    @Transactional
    public void updateRoom(UUID id, RoomDto dto) {
        var oEntity = repository.findById(id);
        if (oEntity.isEmpty()) {
            throw new RoomNotFoundException(HttpStatus.NOT_FOUND, "Room not found");
        }
        var entity = oEntity.get();
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setBedsNumber(dto.getBedsNumber());
        entity.setFloorNumber(dto.getFloorNumber());
        entity.setPrice(dto.getPrice());

        var roomType = roomTypeService.readOneType(dto.getRoomType().getId());
        entity.setRoomType(referenceMapper.toRoomType(roomType.getId()));

        var accommodationFacility = accommodationFacilityService.readOneAccommodationFacility(
                dto.getAccommodationFacility().getId());
        entity.setAccommodationFacility(referenceMapper.toAccommodationFacility(accommodationFacility.getId()));
    }

    @Override
    public void deleteRoom(UUID id) {
        repository.deleteById(id);
    }
}
