package com.example.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class BookingDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull private Instant bookingDate;

    @NotNull private Instant dateCheckIn;

    @NotNull private Instant dateCheckOut;

    @NotNull private RoomDto room;

    @NotNull private CustomerDto customer;

    private Integer peopleNumber;
}
