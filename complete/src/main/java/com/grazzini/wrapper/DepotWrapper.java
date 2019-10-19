package com.grazzini.wrapper;

import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.Depot;
import org.springframework.beans.factory.annotation.Autowired;

public class DepotWrapper {

    @Autowired
    public DepotRepository depotRepository;

    @Autowired
    public BusVehiculeWrapper busVehiculeWrapper;

    public Depot validateAndCreateNewDepot(Depot depot){
        if(validateDepot(depot)){
            return depotRepository.save(depot);
        }
        return null;
    }
    public boolean validateDepot(Depot depot){
        if(depot.getBusCapacity() == null || depot.getBusCapacity() <= 0){
            return false;
        }
        if(depot.getName() == null || depot.getName().equals("")){
            return false;
        }
        if(depotRepository.findByName(depot.getName()) != null){
            return false;
        }
        return true;
    }

    public void addNewBusVehicleToDepot(Depot depot, BusVehicle busVehicle) {
        if(depot.getBusCapacity() > depot.getBusVehiclesParked().size()){
            busVehicle.setDepotParkedIn(depot);
            busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);
        }
    }
}
