package com.grazzini.wrapper;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.helper.NumberHelper;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.Depot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DepotWrapper {

    @Autowired
    public DepotRepository depotRepository;
    @Autowired
    public BusVehiculeWrapper busVehiculeWrapper;
    @Autowired
    BusVehicleRepository busVehicleRepository;

    public Depot validateAndCreateNewDepot(String depotId, String depotName, String depotCapacity, String[] busVehicleSelectedIds) {
        if(!NumberHelper.isStringNumeric(depotCapacity)){
            return null;
        }
        Depot depot = getDepotOrReturnNewDepot(depotId);
        depot.setName(depotName);
        depot.setBusCapacity(Integer.parseInt(depotCapacity));
        List<Long> listIds = generateListIdsFromArray(busVehicleSelectedIds);
        List<BusVehicle> busVehiclesInDepot = busVehicleRepository.findAllById(listIds);
        depot.setBusVehiclesParked(busVehiclesInDepot);
        return validateAndCreateNewDepot(depot);
    }

    private Depot getDepotOrReturnNewDepot(String depotIdString) {
        Depot depot = getDepotFromStringId(depotIdString);
        if(depot == null){
            depot = new Depot();
        }
        return depot;
    }

    public Depot getDepotFromStringId(String depotIdString){
        if(!NumberHelper.isStringNumeric(depotIdString)){
            return new Depot();
        }
        Long depotId = Long.parseLong(depotIdString);
        Depot depot = depotRepository.findById(depotId).get();
        return depot;
    }

    private List<Long> generateListIdsFromArray(String[] busVehicleSelectedIds) {
        List<Long> ids = new ArrayList<>();
        if(busVehicleSelectedIds == null){
            return ids;
        }
        for(String s : busVehicleSelectedIds){
            if(NumberHelper.isStringNumeric(s)){
                ids.add(Long.parseLong(s));
            }
        }
        return ids;
    }

    public Depot validateAndCreateNewDepot(Depot depot){
        if(validateDepot(depot)){
            depot = depotRepository.save(depot);
            for(BusVehicle b : depot.getBusVehiclesParked()){
                addExistingBusVehicleToDepot(depot, b);
            }
            return depot;
        }
        return null;
    }

    private boolean validateDepot(Depot depot){
        if(depot.getBusCapacity() == null || depot.getBusCapacity() <= 0){
            return false;
        }
        if(depot.getName() == null || depot.getName().equals("")){
            return false;
        }
        Depot byName = depotRepository.findByName(depot.getName());
        if(byName != null && byName.getId() != depot.getId()){
            return false;
        }
        return true;
    }

    private void addExistingBusVehicleToDepot(Depot depot, BusVehicle b) {
        if(b == null || b.getId() == null){
            return;
        }
        BusVehicle busVehicle = busVehicleRepository.findById(b.getId()).get();
        if(busVehicle == null){
            return;
        }
        busVehicle.setDepotParkedIn(depot);
        busVehicleRepository.save(busVehicle);
    }

    public void addNewBusVehicleToDepot(Depot depot, BusVehicle busVehicle) {
        if(depot.getBusCapacity() > depot.getBusVehiclesParked().size()){
            busVehicle.setDepotParkedIn(depot);
            busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);
        }
    }

}
