package com.grazzini.views;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.BusVehicleColor;
import com.grazzini.model.BusVehicleType;
import com.grazzini.model.Depot;
import com.grazzini.wrapper.BusVehiculeWrapper;
import com.grazzini.wrapper.DepotWrapper;

import java.util.Random;


public class TestHelper {

    private BusVehicleRepository busVehicleRepository;
    private DepotRepository depotRepository;
    private BusVehiculeWrapper busVehiculeWrapper;
    private DepotWrapper depotWrapper;
    Random rand = new Random();

    public TestHelper(BusVehicleRepository busVehicleRepository, DepotRepository depotRepository, BusVehiculeWrapper busVehiculeWrapper, DepotWrapper depotWrapper) {
        this.busVehicleRepository = busVehicleRepository;
        this.depotRepository = depotRepository;
        this.busVehiculeWrapper = busVehiculeWrapper;
        this.depotWrapper = depotWrapper;
    }

    public void cleanDatabase() {
        busVehicleRepository.deleteAll();
        depotRepository.deleteAll();
    }

    public Depot createDepotWithBusVehicles(String depotName, int nbrBusVehicle){
        return createDepotWithBusVehicles(depotName, nbrBusVehicle, nbrBusVehicle);
    }

    public Depot createDepotWithBusVehicles(String depotName, int nbrBusVehicle, int busVehicleCapacity){
        Depot newDepot = new Depot(depotName, busVehicleCapacity);
        newDepot = depotWrapper.validateAndCreateNewDepot(newDepot);
        for(int i = 0; i < nbrBusVehicle; i++){
            depotWrapper.addNewBusVehicleToDepot(newDepot, new BusVehicle(generateNewBusVehiclePlateNumber(), BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30));
        }
        return depotRepository.findByName(newDepot.getName());
    }

    public String generateNewBusVehiclePlateNumber(){
        String newPlateNumber = "";
        Integer nextInt;
        do{
            newPlateNumber = "BUS-";
            nextInt = rand.nextInt(1000);
            if(nextInt < 100){newPlateNumber += "0";}
            if(nextInt < 10){newPlateNumber += "0";}
            newPlateNumber += nextInt.toString() + "-";
            nextInt = rand.nextInt(1000);
            if(nextInt < 100){newPlateNumber += "0";}
            if(nextInt < 10){newPlateNumber += "0";}
            newPlateNumber += nextInt.toString();
        }while(busVehicleRepository.findByPlateNumber(newPlateNumber).size() > 0);
        return newPlateNumber;
    }
}
