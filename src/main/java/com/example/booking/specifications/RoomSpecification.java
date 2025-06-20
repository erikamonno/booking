package com.example.booking.specifications;

import com.example.booking.entities.Room;
import com.example.booking.filters.RoomFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class RoomSpecification implements Specification<Room> {

    private final RoomFilter filter;

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                        roomNumberEqual(),
                        bedsNumberEqual(),
                        floorNumberEqual(),
                        priceEqual(),
                        idRoomTypeEqual(),
                        idHotelEqual())
                .toPredicate(root, query, criteriaBuilder);
    }

    private Specification<Room> roomNumberEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getRoomNumber() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("roomNumber"), filter.getRoomNumber());
            }
        };
    }

    private Specification<Room> bedsNumberEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getRoomNumber() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("bedsNumber"), filter.getBedsNumber());
            }
        };
    }

    private Specification<Room> floorNumberEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getFloorNumber() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("floorNUmber"), filter.getFloorNumber());
            }
        };
    }

    private Specification<Room> priceEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getPrice() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("price"), filter.getPrice());
            }
        };
    }

    private Specification<Room> idRoomTypeEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getIdRoomType() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("roomType").get("id"), filter.getIdRoomType());
            }
        };
    }

    private Specification<Room> idHotelEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getIdHotel() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("hotel").get("id"), filter.getIdHotel());
            }
        };
    }
}
