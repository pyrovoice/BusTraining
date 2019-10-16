package com.grazzini.dao;

import com.grazzini.model.BusVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BusVehicleRepository extends JpaRepository<BusVehicle, Long> {
    public List<BusVehicle> findByDepotParkedInNull();
}
