package com.example.booking.specifications;

import com.example.booking.entities.RoomType;
import com.example.booking.filters.RoomTypeFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class RoomTypeSpecification implements Specification<RoomType> {

    private final RoomTypeFilter filter;

    @Override
    public Predicate toPredicate(Root<RoomType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(nameEqual(), increasePercentageEqual()).toPredicate(root, query, criteriaBuilder);
    }

    public Specification<RoomType> nameEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getName() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("name"), filter.getName());
            }
        };
    }

    public Specification<RoomType> increasePercentageEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getIncreasePercentage() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("increasePercentage"), filter.getIncreasePercentage());
            }
        };
    }
}
