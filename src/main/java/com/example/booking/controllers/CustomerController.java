package com.example.booking.controllers;

import com.example.booking.dto.CustomerDto;
import com.example.booking.filters.CustomerFilter;
import com.example.booking.servicies.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDto insertCustomer(@RequestBody CustomerDto dto) {
        return service.insertCustomer(dto);
    }

    @GetMapping("{id}")
    public CustomerDto readOneCustomer(@PathVariable Long id) {
        return service.readOneCustomer(id);
    }

    @GetMapping
    public Page<CustomerDto> searchCustomer(@PageableDefault(sort = "id") Pageable pageable, @Valid CustomerFilter filter) {
        return service.searchCustomer(pageable, filter);
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto dto) {
        service.updateCustomer(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.delteCustomer(id);
    }
}
