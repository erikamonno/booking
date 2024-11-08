package com.example.booking.servicies;

import com.example.booking.dto.CustomerDto;
import com.example.booking.entities.Customer;
import com.example.booking.exceptions.CustomerNotFound;
import com.example.booking.filters.CustomerFilter;
import com.example.booking.mappers.CustomerMapper;
import com.example.booking.repositories.CustomerRepository;
import com.example.booking.servicies.CustomerService;
import com.example.booking.specifications.CustomerSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public CustomerDto insertCustomer(CustomerDto dto) {
        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public CustomerDto readOneCustomer(Long id) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new CustomerNotFound("Customer not found");
        }
        var entity = oEntity.get();
        return mapper.toDto(entity);
    }

    @Override
    public Page<CustomerDto> searchCustomer(Pageable pageable, CustomerFilter filter) {
        return repository.findAll(new CustomerSpecification(filter), pageable).map(customer -> mapper.toDto(customer));
    }

    @Transactional
    @Override
    public void updateCustomer(Long id, CustomerDto dto) {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new CustomerNotFound("Customer not found");
        }
        var entity = oEntity.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }

    @Override
    public void delteCustomer(Long id) {
        repository.deleteById(id);
    }
}
