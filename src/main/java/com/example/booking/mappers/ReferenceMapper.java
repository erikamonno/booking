package com.example.booking.mappers;

import com.example.booking.entities.*;
import com.example.booking.repositories.*;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ReferenceMapper {

    private final CustomerRepository customerRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final AccommodationFacilityRepository accommodationFacilityRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public ReferenceMapper(
            CustomerRepository customerRepository,
            RoomTypeRepository roomTypeRepository,
            AccommodationFacilityRepository accommodationFacilityRepository,
            RoomRepository roomRepository,
            BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.accommodationFacilityRepository = accommodationFacilityRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public Customer toCustomer(UUID id) {
        return customerRepository.getReferenceById(id);
    }

    public RoomType toRoomType(UUID id) {
        return roomTypeRepository.getReferenceById(id);
    }

    public AccommodationFacility toAccommodationFacility(UUID id) {
        return accommodationFacilityRepository.getReferenceById(id);
    }

    public Room toRoom(UUID id) {
        return roomRepository.getReferenceById(id);
    }

    public Booking toBooking(UUID id) {
        return bookingRepository.getReferenceById(id);
    }
}
