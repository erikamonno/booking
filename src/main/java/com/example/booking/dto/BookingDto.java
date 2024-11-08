package com.example.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class BookingDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private Instant bookingDate;
    @NotNull
    private Instant dateCheckIn;
    @NotNull
    private Instant dateCheckOut;
    @NotNull
    private Long idRoom;
    @NotNull
    private Long idCustomer;
    private Integer peopleNumber;

}
