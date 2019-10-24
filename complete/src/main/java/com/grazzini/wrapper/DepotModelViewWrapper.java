package com.grazzini.wrapper;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.helper.NumberHelper;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.Depot;
import com.grazzini.modelview.DepotModelView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DepotModelViewWrapper {

    @Autowired
    public DepotRepository depotRepository;
    @Autowired
    public BusVehicleRepository busVehicleRepository;

    public Depot getDepotFromModelView(DepotModelView depotModel){
        Depot depot = null;
        if(depotModel.getDepotId() != null){
            depot = depotRepository.findById(depotModel.getDepotId()).get();
        }
        if(depot == null){
            depot = new Depot();
        }
        depot.setName(depotModel.getDepotName());
        depot.setBusCapacity(depotModel.getDepotCapacity());
        depot.setName(depotModel.getDepotName());

        List<BusVehicle> busVehicles = busVehicleRepository.findAllById(depotModel.getBusVehicleIds());
        depot.setBusVehiclesParked(new ArrayList<>(busVehicles));

        return depot;
    }

}
