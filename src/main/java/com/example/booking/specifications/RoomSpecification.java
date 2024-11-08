package com.example.booking.specifications;

import com.example.booking.dto.RoomDto;
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
                roomNumberEqual(filter.getRoomNumber()),
                bedsNumberEqual(filter.getBedsNumber()),
                floorNumberEqual(filter.getFloorNumber()),
                priceEqual(filter.getPrice()),
                idRoomTypeEqual(filter.getIdRoomType()),
                idHotelEqual(filter.getIdHotel())
        ).toPredicate(root, query, criteriaBuilder);
    }

    private Specification<Room> roomNumberEqual(Integer roomNumber) {
        return (root, query, criteriaBuilder) -> {
            if(roomNumber==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("roomNumber"), roomNumber);
            }
        };
    }

    private Specification<Room> bedsNumberEqual(Integer bedsNumber) {
        return (root, query, criteriaBuilder) -> {
            if(bedsNumber==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("bedsNumber"), bedsNumber);
            }
        };
    }

    private Specification<Room> floorNumberEqual(Integer floorNumber) {
        return (root, query, criteriaBuilder) -> {
            if(floorNumber==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("floorNUmber"), floorNumber);
            }
        };
    }

    private Specification<Room> priceEqual(Double price) {
        return (root, query, criteriaBuilder) -> {
            if(price==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("price"), price);
            }
        };
    }

    private Specification<Room> idRoomTypeEqual(Long idRoomType) {
        return (root, query, criteriaBuilder) -> {
            if(idRoomType==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("roomType").get("id"), idRoomType);
            }
        };
    }

    private Specification<Room> idHotelEqual(Long idHotel) {
        return (root, query, criteriaBuilder) -> {
            if(idHotel==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("hotel").get("id"), idHotel);
            }
        };
    }
}
