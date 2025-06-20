package com.example.booking.services;

import com.example.booking.dto.BookingDto;
import com.example.booking.dto.RoomDto;
import com.example.booking.entities.Booking;
import com.example.booking.exceptions.BookingNotFoundException;
import com.example.booking.exceptions.InsufficientBedsNumberException;
import com.example.booking.exceptions.NoRoomAvailableException;
import com.example.booking.filters.BookingFilter;
import com.example.booking.mappers.BookingMapper;
import com.example.booking.mappers.ReferenceMapper;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.specifications.BookingSpecification;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final BookingMapper mapper;
    private final RoomService roomService;
    private final CustomerService customerService;
    private final ReferenceMapper referenceMapper;

    public BookingServiceImpl(
            BookingRepository repository,
            BookingMapper mapper,
            RoomService roomService,
            CustomerService customerService,
            ReferenceMapper referenceMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.roomService = roomService;
        this.customerService = customerService;
        this.referenceMapper = referenceMapper;
    }

    @Override
    public BookingDto insertBooking(BookingDto dto) {
        Booking entity = new Booking();
        var room = roomService.readOneRoom(dto.getRoom().getId());
        var customer = customerService.readOneCustomer(dto.getCustomer().getId());

        checkBedsNumber(dto, room);

        checkIfDateIsAvailable(dto);

        entity.setBookingDate(dto.getBookingDate());
        entity.setDateCheckIn(dto.getDateCheckIn());
        entity.setDateCheckOut(dto.getDateCheckOut());
        entity.setRoom(referenceMapper.toRoom(room.getId()));
        entity.setCustomer(referenceMapper.toCustomer(customer.getId()));
        entity.setPeopleNumber(dto.getPeopleNumber());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    private void checkIfDateIsAvailable(BookingDto dto) {
        BookingFilter filter = new BookingFilter();
        filter.setDateCheckIn(dto.getDateCheckIn());
        filter.setDateCheckOut(dto.getDateCheckOut());
        filter.setIdRoom(dto.getRoom().getId());
        var bookedRooms = searchBooking(Pageable.ofSize(1), filter);
        if (!bookedRooms.isEmpty()) {
            throw new NoRoomAvailableException(HttpStatus.CONFLICT, "No room available");
        }
    }

    private void checkBedsNumber(BookingDto bookingDto, RoomDto roomDto) {
        if (bookingDto.getPeopleNumber() > roomDto.getBedsNumber()) {
            throw new InsufficientBedsNumberException(HttpStatus.BAD_REQUEST, "Insufficient beds number");
        }
    }

    @Override
    public BookingDto readOneBooking(UUID id) {
        var oEntity = repository.findById(id);
        if (oEntity.isEmpty()) {
            throw new BookingNotFoundException(HttpStatus.NOT_FOUND, "Booking not found");
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
    public void updateBooking(UUID id, BookingDto dto) {
        var oEntity = repository.findById(id);
        var room = roomService.readOneRoom(dto.getRoom().getId());
        var customer = customerService.readOneCustomer(dto.getCustomer().getId());
        if (oEntity.isEmpty()) {
            throw new BookingNotFoundException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        checkBedsNumber(dto, room);

        checkIfDateIsAvailable(dto);

        var entity = oEntity.get();
        entity.setBookingDate(dto.getBookingDate());
        entity.setDateCheckIn(dto.getDateCheckIn());
        entity.setDateCheckOut(dto.getDateCheckOut());
        entity.setDateCheckOut(dto.getDateCheckOut());
        entity.setRoom(referenceMapper.toRoom(room.getId()));
        entity.setCustomer(referenceMapper.toCustomer(customer.getId()));
        entity.setPeopleNumber(dto.getPeopleNumber());
    }

    @Override
    public void deleteBooking(UUID id) {
        repository.deleteById(id);
    }
}
