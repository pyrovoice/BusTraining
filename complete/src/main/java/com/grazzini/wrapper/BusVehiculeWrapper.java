package com.grazzini.wrapper;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.model.BusVehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class BusVehiculeWrapper {

    @Autowired
    public BusVehicleRepository busVehicleRepository;
    private static Pattern PLATE_NUMBER_PATTERN = Pattern.compile("BUS-\\d{3}-\\d{3}");

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
        if(busVehicleRepository.findByPlateNumber(busVehicule.getPlateNumber()).size() > 0){
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
