package com.example.booking.filters;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class BookingFilter {
    private Instant bookingDate;
    private Instant dateCheckIn;
    private Instant dateCheckOut;
    private UUID idRoom;
    private UUID idCustomer;
}
