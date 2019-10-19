package com.grazzini.dao;

import com.grazzini.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {

    @Query("SELECT d FROM DEPOT d WHERE d.busCapacity > (SELECT COUNT(b) FROM BUSVEHICULE b, DEPOT d WHERE b.depotParkedIn = d.id)")
    public List<Depot> findAllDepotAvailableSpace();

    Depot findByName(String name);
}
