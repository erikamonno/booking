package com.example.booking.filters;

import lombok.Data;
import java.time.Instant;

@Data
public class BookingFilter {
    private Instant bookingDate;
    private Instant dateCheckIn;
    private Instant dateCheckOut;
    private Long idRoom;
    private Long idCustomer;
}
