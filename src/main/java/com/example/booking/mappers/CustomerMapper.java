package com.example.booking.mappers;

import com.example.booking.dto.CustomerDto;
import com.example.booking.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer entity);
    Customer toEntity(CustomerDto dto);
}
