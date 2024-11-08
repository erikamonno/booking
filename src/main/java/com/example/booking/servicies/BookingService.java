package com.example.booking.servicies;

import com.example.booking.dto.BookingDto;
import com.example.booking.filters.BookingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface BookingService {
    BookingDto insertBooking(BookingDto dto);
    BookingDto readOneBooking(Long id);
    Page<BookingDto> searchBooking(Pageable pageable, BookingFilter filter);
    void updateBooking(Long id, BookingDto dto);
    void deleteBooking(Long id);
}
