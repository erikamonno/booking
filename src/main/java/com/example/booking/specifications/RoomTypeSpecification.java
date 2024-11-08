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

    private RoomTypeFilter filter;

    public RoomTypeSpecification(RoomTypeFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<RoomType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                nameEqual(filter.getName()),
                increasePercentageEqual(filter.getIncreasePercentage())
        ).toPredicate(root, query, criteriaBuilder);
    }

    private Specification<RoomType> nameEqual(String name) {
        return (root, query, criteriaBuilder) -> {
            if(name==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }

    private Specification<RoomType> increasePercentageEqual(Integer increasePercentage) {
        return (root, query, criteriaBuilder) -> {
            if(increasePercentage==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("increasePercentage"), increasePercentage);
            }
        };
    }
}
