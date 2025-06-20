package com.example.booking.repositories;

import com.example.booking.entities.AccommodationFacility;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccommodationFacilityRepository
        extends JpaRepository<AccommodationFacility, UUID>, JpaSpecificationExecutor<AccommodationFacility> {}
