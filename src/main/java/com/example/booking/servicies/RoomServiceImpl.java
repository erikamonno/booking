package com.example.booking.servicies;

import com.example.booking.dto.RoomDto;
import com.example.booking.entities.Room;
import com.example.booking.exceptions.RoomNotFound;
import com.example.booking.filters.RoomFilter;
import com.example.booking.mappers.HotelMapper;
import com.example.booking.mappers.RoomMapper;
import com.example.booking.mappers.RoomTypeMapper;
import com.example.booking.repositories.RoomRepository;
import com.example.booking.specifications.RoomSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomMapper mapper;
    private final RoomTypeMapper roomTypeMapper;
    private final RoomRepository repository;
    private final RoomTypeService roomTypeService;
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    public RoomServiceImpl(RoomMapper mapper, RoomTypeMapper roomTypeMapper, RoomRepository repository, RoomTypeService roomTypeService, HotelService hotelService, HotelMapper hotelMapper) {
        this.mapper = mapper;
        this.roomTypeMapper = roomTypeMapper;
        this.repository = repository;
        this.roomTypeService = roomTypeService;
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public RoomDto insertRoom(RoomDto dto) {
        log.info("RoomService.insertType() invoked with parameters {}", dto);
        Room entity = new Room();
        entity.setId(dto.getId());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setBedsNumber(dto.getBedsNumber());
        entity.setFloorNumber(dto.getFloorNumber());
        entity.setPrice(dto.getPrice());
        entity.setRoomType(roomTypeMapper.toEntity(roomTypeService.readOneType(dto.getIdRoomType())));
        entity.setHotel(hotelMapper.toEntity(hotelService.readOneHotel(dto.getIdHotel())));
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public RoomDto readOneRoom(Long id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new RoomNotFound("Room not found");
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
    public void updateRoom(Long id, RoomDto dto) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()){
            throw new RoomNotFound("Room not found");
        }
        var entity = oEntity.get();
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setBedsNumber(dto.getBedsNumber());
        entity.setFloorNumber(dto.getFloorNumber());
        entity.setPrice(dto.getPrice());

        var roomType = roomTypeService.readOneType(dto.getIdRoomType());
        entity.setRoomType(roomTypeMapper.toEntity(roomType));

        var hotel = hotelService.readOneHotel(dto.getIdHotel());
        entity.setHotel(hotelMapper.toEntity(hotel));
    }

    @Override
    public void deleteRoom(Long id) {
        repository.deleteById(id);
    }
}
