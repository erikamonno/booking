package com.example.booking.servicies;
import com.example.booking.dto.CustomerDto;
import com.example.booking.filters.CustomerFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDto insertCustomer(CustomerDto dto);
    CustomerDto readOneCustomer(Long id);
    Page<CustomerDto> searchCustomer(Pageable pageable, CustomerFilter filter);
    void updateCustomer(Long id, CustomerDto dto);
    void delteCustomer(Long id);
}
