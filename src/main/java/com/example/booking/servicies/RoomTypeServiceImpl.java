package com.example.booking.servicies;

import com.example.booking.dto.RoomTypeDto;
import com.example.booking.entities.RoomType;
import com.example.booking.exceptions.RoomTypeNotFound;
import com.example.booking.filters.RoomTypeFilter;
import com.example.booking.mappers.RoomTypeMapper;
import com.example.booking.repositories.RoomTypeRepository;
import com.example.booking.specifications.RoomTypeSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeMapper mapper;
    private final RoomTypeRepository repository;

    public RoomTypeServiceImpl(RoomTypeMapper mapper, RoomTypeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public RoomTypeDto insertType(RoomTypeDto dto) {
        log.info("TypeOfRoomService.insertType() invoked with parameters {}", dto);
        RoomType entity = new RoomType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIncreasePercentage(dto.getIncreasePercentage());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public RoomTypeDto readOneType(Long id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new RoomTypeNotFound("Type of room not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<RoomTypeDto> searchType(Pageable pageable, RoomTypeFilter filter) {
        return repository.findAll(new RoomTypeSpecification(filter), pageable).map(typeOfRoom -> mapper.toDto(typeOfRoom));
    }

    @Transactional
    @Override
    public void updateType(RoomTypeDto dto, Long id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new RoomTypeNotFound("Type of room not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setIncreasePercentage(dto.getIncreasePercentage());
    }

    @Override
    public void deleteType(Long id) {
        repository.deleteById(id);
    }
}