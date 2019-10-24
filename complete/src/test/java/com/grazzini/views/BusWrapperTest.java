package com.grazzini.views;

import com.grazzini.model.BusVehicle;
import com.grazzini.model.BusVehicleColor;
import com.grazzini.model.BusVehicleType;
import com.grazzini.model.Depot;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BusWrapperTest extends BaseTestClass {



    @Test
    public void testValidateAndCreateNewBusVehicle(){
        String plateNumberName = "BUS-000-000";
        BusVehicle busVehicle = new BusVehicle(plateNumberName, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findByPlateNumber(plateNumberName);
        Assert.assertEquals(1, retrievedBusVehicle.size());
        Assert.assertEquals(plateNumberName, retrievedBusVehicle.get(0).getPlateNumber());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleWithDepot(){
        Depot depot = depotWrapper.validateAndCreateNewDepot(new Depot("newDepot", 5));
        String plateNumberName = "BUS-000-000";
        BusVehicle busVehicle = new BusVehicle(plateNumberName, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30, depot);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findByPlateNumber(plateNumberName);
        Assert.assertEquals(1, retrievedBusVehicle.size());
        BusVehicle createdBusvehicle = retrievedBusVehicle.get(0);
        Assert.assertEquals(plateNumberName, createdBusvehicle.getPlateNumber());
        Assert.assertEquals("newDepot", createdBusvehicle.getDepotParkedIn().getName());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleEmptyName(){
        String plateNumberName = "";
        BusVehicle busVehicle = new BusVehicle(plateNumberName, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleNullName(){
        BusVehicle busVehicle = new BusVehicle(null, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }
    @Test
    public void testValidateAndCreateNewBusVehicleName4rthNumber(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-0000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleNameLowercase(){
        BusVehicle busVehicle = new BusVehicle("Bus-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleNameRandomStartingLetter(){
        BusVehicle busVehicle = new BusVehicle("SUB-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleNameAllNines(){
        BusVehicle busVehicle = new BusVehicle("BUS-999-999", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(1, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleSameName(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);
        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);
        busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.DOUBLE_DECKER, BusVehicleColor.ORANGE, 45);
        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(1, retrievedBusVehicle.size());
    }
    @Test
    public void testValidateAndCreateNewBusVehicleColorNull(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, null, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleTypeNull(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", null, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleCapacity0(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 0);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleCapacityMinus1(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, -1);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testValidateAndCreateNewBusVehicleCapacity71(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 71);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(0, retrievedBusVehicle.size());
    }

    @Test
    public void testCreateNewVehicleSameIdReplacePrevious(){
        BusVehicle busVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);

        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);
        Long createdBusVehicleId = busVehicleRepository.findAll().get(0).getId();
        busVehicle = new BusVehicle("BUS-000-001", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);
        busVehicle.setId(createdBusVehicleId);
        busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicle);

        List<BusVehicle> retrievedBusVehicle = busVehicleRepository.findAll();
        Assert.assertEquals(1, retrievedBusVehicle.size());
        Assert.assertEquals("BUS-000-001", retrievedBusVehicle.get(0).getPlateNumber());
    }

    @Test
    public void testNewBusVehicleHasNoDepotByDefault(){
        testHelper.createDepotWithBusVehicles("Depot", 0, 5);
        BusVehicle newBusVehicle = new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);
        busVehiculeWrapper.validateAndCreateNewBusVehicle(newBusVehicle);
        Depot defaultDepot = busVehicleRepository.findAll().get(0).getDepotParkedIn();
        Assert.assertNull(defaultDepot);
        Depot previouslyCreatedDepot = depotRepository.findAll().get(0);
        Assert.assertEquals(0, previouslyCreatedDepot.getBusVehiclesParked().size());
    }
}
