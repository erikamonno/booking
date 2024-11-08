package com.example.booking.specifications;

import com.example.booking.entities.Hotel;
import com.example.booking.filters.HotelFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class HotelSpecification implements Specification<Hotel> {

    private HotelFilter filter;

    public HotelSpecification(HotelFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                nameEqual(filter.getName()),
                addressEqual(filter.getAddress()),
                phoneEqual(filter.getPhone()),
                mailEqual(filter.getMail())
        ).toPredicate(root, query, criteriaBuilder);
    }

    private static Specification<Hotel> nameEqual(String name) {
        return (root, query, criteriaBuilder) -> {
            if(name==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }

    private static Specification<Hotel> addressEqual(String address) {
        return (root, query, criteriaBuilder) -> {
            if(address==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("address"), address);
            }
        };
    }

    private static Specification<Hotel> phoneEqual(String phone) {
        return (root, query, criteriaBuilder) -> {
            if(phone==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("phone"), phone);
            }
        };
    }

    private static Specification<Hotel> mailEqual(String mail) {
        return (root, query, criteriaBuilder) -> {
            if(mail==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("mail"), mail);
            }
        };
    }
}
