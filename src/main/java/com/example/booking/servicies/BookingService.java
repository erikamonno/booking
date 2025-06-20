package com.example.booking.servicies;

import com.example.booking.dto.BookingDto;
import com.example.booking.filters.BookingFilter;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    BookingDto insertBooking(BookingDto dto);

    BookingDto readOneBooking(UUID id);

    Page<BookingDto> searchBooking(Pageable pageable, BookingFilter filter);

    void updateBooking(UUID id, BookingDto dto);

    void deleteBooking(UUID id);
}
