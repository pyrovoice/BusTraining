package com.grazzini.wrapper;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.helper.NumberHelper;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.BusVehicleColor;
import com.grazzini.model.BusVehicleType;
import com.grazzini.model.Depot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.regex.Pattern;

public class BusVehiculeWrapper {

    @Autowired
    public BusVehicleRepository busVehicleRepository;
    @Autowired public DepotWrapper depotWrapper;
    private static Pattern PLATE_NUMBER_PATTERN = Pattern.compile("BUS-\\d{3}-\\d{3}");

    public BusVehicle validateAndCreateNewBusVehicle(String busVehicleId, String busVehiclePlateNumber, String busVehiclePassengerCapacity, String busVehicleColor, String busVehicleType, String busVehicleDepotParkedIn) {
        if(!NumberHelper.isStringNumeric(busVehiclePassengerCapacity)){
            return null;
        }
        BusVehicle busVehicle = getBusVehicleOrReturnNewBusVehicle(busVehicleId);

        busVehicle.setPlateNumber(busVehiclePlateNumber);
        busVehicle.setPassengerCapacity(Integer.parseInt(busVehiclePassengerCapacity));
        busVehicle.setColor(BusVehicleColor.valueOf(busVehicleColor));
        busVehicle.setType(BusVehicleType.valueOf(busVehicleType));
        Depot depotFromStringId = depotWrapper.getDepotFromStringId(busVehicleDepotParkedIn);
        busVehicle.setDepotParkedIn(depotFromStringId);
        return validateAndCreateNewBusVehicle(busVehicle);
    }

    private BusVehicle getBusVehicleOrReturnNewBusVehicle(String busVehicleIdString) {
        if(!NumberHelper.isStringNumeric(busVehicleIdString)){
            return new BusVehicle();
        }
        Long busVehicleId = Long.parseLong(busVehicleIdString);
        BusVehicle busVehicle = busVehicleRepository.findById(busVehicleId).get();
        if(busVehicle == null){
            busVehicle = new BusVehicle();
        }
        return busVehicle;
    }

    public BusVehicle validateAndCreateNewBusVehicle(BusVehicle busVehicle) {
        if(validateBusVehicle(busVehicle)){
            return busVehicleRepository.save(busVehicle);
        }
        return null;
    }

    private boolean validateBusVehicle(BusVehicle busVehicule){
        if(busVehicule.getPlateNumber() == null){
            return false;
        }
        boolean plateNumberValid = PLATE_NUMBER_PATTERN.matcher(busVehicule.getPlateNumber()).matches();

        List<BusVehicle> byPlateNumber = busVehicleRepository.findByPlateNumber(busVehicule.getPlateNumber());
        if(byPlateNumber.size() > 0 && byPlateNumber.get(0).getId() != busVehicule.getId()){
            return false;
        }
        if(!plateNumberValid){
            return false;
        }
        if(busVehicule.getType() == null){
            return false;
        }
        if(busVehicule.getColor() == null){
            return false;
        }
        if(busVehicule.getPassengerCapacity() < 1 || busVehicule.getPassengerCapacity() > 70){
            return false;
        }
        return true;
    }



}
