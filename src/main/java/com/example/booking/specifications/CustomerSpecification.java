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
    private CustomerFilter filter;

    public CustomerSpecification(CustomerFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                nameEqual(filter.getName()),
                surnameEqual(filter.getSurname()),
                emailEqual(filter.getEmail()),
                phoneEqual(filter.getPhone())
        ).toPredicate(root, query, criteriaBuilder);
    }

    private static Specification<Customer> nameEqual(String name) {
        return (root, query, criteriaBuilder) -> {
            if(name==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }

    private static Specification<Customer> surnameEqual(String surname) {
        return (root, query, criteriaBuilder) -> {
            if(surname==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("surname"), surname);
            }
        };
    }

    private static Specification<Customer> emailEqual(String email) {
        return (root, query, criteriaBuilder) -> {
            if(email==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("email"), email);
            }
        };
    }

    private static Specification<Customer> phoneEqual(String phone) {
        return (root, query, criteriaBuilder) -> {
            if(phone==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("phone"), phone);
            }
        };
    }

}
