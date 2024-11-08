package com.example.booking.servicies;

import com.example.booking.dto.HotelDto;
import com.example.booking.entities.Hotel;
import com.example.booking.exceptions.HotelNotFoundException;
import com.example.booking.filters.HotelFilter;
import com.example.booking.mappers.HotelMapper;
import com.example.booking.repositories.HotelRepository;
import com.example.booking.specifications.HotelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelMapper mapper;
    private final HotelRepository repository;

    public HotelServiceImpl(HotelMapper mapper, HotelRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public HotelDto insertHotel(HotelDto dto) {
        Hotel entity = new Hotel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setMail(dto.getMail());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public HotelDto readOneHotel(Long id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw  new HotelNotFoundException("Hotel not found");
        }
        Hotel entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<HotelDto> searchHotel(HotelFilter filter, Pageable pageable) {
        return repository.findAll(new HotelSpecification(filter), pageable).map(hotel -> mapper.toDto(hotel));
    }

    @Transactional
    @Override
    public void updateHotel(Long id, HotelDto dto) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new HotelNotFoundException("Hotel not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setMail(dto.getMail());
    }

    @Override
    public void deleteHotel(Long id) {
        repository.deleteById(id);
    }
}
