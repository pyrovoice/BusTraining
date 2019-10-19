package com.grazzini.views;

import com.grazzini.model.BusVehicle;
import com.grazzini.model.BusVehicleColor;
import com.grazzini.model.BusVehicleType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BusVehicleRepositoryTest extends BaseTestClass {

    @Test
    public void testGetVehicleWithoutDepot(){
        testHelper.createDepotWithBusVehicles("Depot1", 1);
        String busVehicleWithoutDepotPlateNumber = testHelper.generateNewBusVehiclePlateNumber();
        busVehiculeWrapper.validateAndCreateNewBusVehicle(new BusVehicle(busVehicleWithoutDepotPlateNumber, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30));

        List<BusVehicle> depotParkedInNull = busVehicleRepository.findByDepotParkedInNull();
        Assert.assertEquals(1, depotParkedInNull.size());
        Assert.assertEquals(busVehicleWithoutDepotPlateNumber, depotParkedInNull.get(0).getPlateNumber());

        List<BusVehicle> allBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(2, allBusVehicle.size());

    }
}
