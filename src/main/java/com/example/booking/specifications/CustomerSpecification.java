package com.example.booking.specifications;

import com.example.booking.entities.Customer;
import com.example.booking.filters.CustomerFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class CustomerSpecification implements Specification<Customer> {

    private final CustomerFilter filter;

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(nameEqual(), surnameEqual(), emailEqual(), phoneEqual())
                .toPredicate(root, query, criteriaBuilder);
    }

    public Specification<Customer> nameEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getName() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("name"), filter.getName());
            }
        };
    }

    public Specification<Customer> surnameEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getSurname() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("surname"), filter.getSurname());
            }
        };
    }

    public Specification<Customer> emailEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getEmail() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("email"), filter.getEmail());
            }
        };
    }

    public Specification<Customer> phoneEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getPhone() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("phone"), filter.getPhone());
            }
        };
    }
}
