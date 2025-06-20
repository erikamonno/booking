package com.example.booking.servicies;

import com.example.booking.dto.CustomerDto;
import com.example.booking.filters.CustomerFilter;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDto insertCustomer(CustomerDto dto);

    CustomerDto readOneCustomer(UUID id);

    Page<CustomerDto> searchCustomer(Pageable pageable, CustomerFilter filter);

    void updateCustomer(UUID id, CustomerDto dto);

    void deleteCustomer(UUID id);
}
