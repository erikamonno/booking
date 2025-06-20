package com.example.booking.specifications;

import com.example.booking.entities.Booking;
import com.example.booking.filters.BookingFilter;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class BookingSpecification implements Specification<Booking> {

    private final BookingFilter filter;

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                        bookingDateEqual(),
                        dateCheckInBetween(),
                        dateCheckOutBetween(),
                        idRoomEqual(),
                        idCustomerEqual())
                .toPredicate(root, query, criteriaBuilder);
    }

    public Specification<Booking> bookingDateEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getBookingDate() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("bookingDate"), filter.getBookingDate());
            }
        };
    }

    public Specification<Booking> dateCheckInBetween() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getDateCheckIn() == null || filter.getDateCheckOut() == null) {
                return null;
            } else {
                return criteriaBuilder.between(
                        root.get("dateCheckIn"), filter.getDateCheckIn(), filter.getDateCheckOut());
            }
        };
    }

    public Specification<Booking> dateCheckOutBetween() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getDateCheckOut() == null || filter.getDateCheckIn() == null) {
                return null;
            } else {
                return criteriaBuilder.between(
                        root.get("dateCheckOut"), filter.getDateCheckIn(), filter.getDateCheckOut());
            }
        };
    }

    public Specification<Booking> idRoomEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getIdRoom() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("room").get("id"), filter.getIdRoom());
            }
        };
    }

    public Specification<Booking> idCustomerEqual() {
        return (root, query, criteriaBuilder) -> {
            if (filter.getIdCustomer() == null) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("customer").get("id"), filter.getIdCustomer());
            }
        };
    }
}
