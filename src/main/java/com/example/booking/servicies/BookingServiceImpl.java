package com.example.booking.servicies;

import com.example.booking.dto.BookingDto;
import com.example.booking.dto.RoomDto;
import com.example.booking.entities.Booking;
import com.example.booking.exceptions.BookingNotFound;
import com.example.booking.exceptions.InsufficentBedsNumberException;
import com.example.booking.exceptions.NoRoomAvailableException;
import com.example.booking.filters.BookingFilter;
import com.example.booking.mappers.BookingMapper;
import com.example.booking.mappers.CustomerMapper;
import com.example.booking.mappers.RoomMapper;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.specifications.BookingSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final BookingMapper mapper;
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public BookingServiceImpl(BookingRepository repository, BookingMapper mapper, RoomService roomService, RoomMapper roomMapper, CustomerService customerService, CustomerMapper customerMapper) {
        this.repository = repository;
        this.mapper = mapper;

        this.roomService = roomService;
        this.roomMapper = roomMapper;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public BookingDto insertBooking(BookingDto dto) {
        Booking entity = new Booking();
        var room = roomService.readOneRoom(dto.getIdRoom());
        var customer = customerService.readOneCustomer(dto.getIdCustomer());
        checkBedsNumber(dto, room);
        checkIfDateIsAvailable(dto);
        entity.setBookingDate(dto.getBookingDate());
        entity.setDateCheckIn(dto.getDateCheckIn());
        entity.setDateCheckOut(dto.getDateCheckOut());
        entity.setRoom(roomMapper.toEntity(room));
        entity.setCustomer(customerMapper.toEntity(customer));
        entity.setPeopleNumber(dto.getPeopleNumber());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    private void checkIfDateIsAvailable(BookingDto dto) {
        BookingFilter filter = new BookingFilter();
        filter.setDateCheckIn(dto.getDateCheckIn());
        filter.setDateCheckOut(dto.getDateCheckOut());
        filter.setIdRoom(dto.getIdRoom());
        var bookedRooms = searchBooking(Pageable.ofSize(1), filter);
        if(!bookedRooms.isEmpty()) {
            throw new NoRoomAvailableException("No room available");
        }
    }

    private void checkBedsNumber(BookingDto bookingDto, RoomDto roomDto) {
        if(bookingDto.getPeopleNumber()>roomDto.getBedsNumber()) {
            throw new InsufficentBedsNumberException("Insufficent beds number");
        }
    }

    @Override
    public BookingDto readOneBooking(Long id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new BookingNotFound("Booking not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<BookingDto> searchBooking(Pageable pageable, BookingFilter filter) {
        return repository.findAll(new BookingSpecification(filter), pageable).map(booking -> mapper.toDto(booking));
    }

    @Override
    @Transactional
    public void updateBooking(Long id, BookingDto dto) {
        var oEntity = repository.findById(id);
        var room = roomService.readOneRoom(dto.getIdRoom());
        var customer = customerService.readOneCustomer(dto.getIdCustomer());
        if(oEntity.isEmpty()) {
            throw new BookingNotFound("Booking not found");
        }
        checkBedsNumber(dto, room);
        checkIfDateIsAvailable(dto);
        var entity = oEntity.get();
        entity.setBookingDate(dto.getBookingDate());
        entity.setDateCheckIn(dto.getDateCheckIn());
        entity.setDateCheckOut(dto.getDateCheckOut());
        entity.setDateCheckOut(dto.getDateCheckOut());
        entity.setRoom(roomMapper.toEntity(room));
        entity.setCustomer(customerMapper.toEntity(customer));
        entity.setPeopleNumber(dto.getPeopleNumber());
    }

    @Override
    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }
}
