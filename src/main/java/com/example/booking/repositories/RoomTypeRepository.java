package com.example.booking.repositories;

import com.example.booking.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long>, JpaSpecificationExecutor<RoomType> {

}
