package com.example.booking.specifications;

import com.example.booking.entities.AccommodationFacility;
import com.example.booking.filters.AccommodationFacilityFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class AccommodationFacilitySpecification implements Specification<AccommodationFacility> {

    private final AccommodationFacilityFilter filter;

    @Override
    public Predicate toPredicate(
            Root<AccommodationFacility> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(nameEqual(), addressEqual(), phoneEqual(), mailEqual())
                .toPredicate(root, query, criteriaBuilder);
    }

    public Specification<AccommodationFacility> nameEqual() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), filter.getName());
        };
    }

    public Specification<AccommodationFacility> addressEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getAddress() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("address"), filter.getAddress());
            }
        };
    }

    public Specification<AccommodationFacility> phoneEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getPhone() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("phone"), filter.getPhone());
            }
        };
    }

    public Specification<AccommodationFacility> mailEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getMail() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("mail"), filter.getMail());
            }
        };
    }
}
