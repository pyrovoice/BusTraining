package com.grazzini.views;

import com.grazzini.model.BusVehicle;
import com.grazzini.model.BusVehicleColor;
import com.grazzini.model.BusVehicleType;
import com.grazzini.model.Depot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepotWrapperTest extends BaseTestClass{

    @Test
    public void validateAndCreateNewDepot(){
        String depotName = "DepotName";
        Depot newDepot = new Depot(depotName, 5);

        depotWrapper.validateAndCreateNewDepot(newDepot);
        List<Depot> retrievedDepots = depotRepository.findAll();

        Assert.assertEquals(1, retrievedDepots.size());
        Depot retrievedDepot = retrievedDepots.get(0);
        Assert.assertEquals(depotName, retrievedDepot.getName());
        Assert.assertEquals(5, retrievedDepot.getBusCapacity().intValue());
    }

    @Test
    public void validateAndCreateNewDepotWithBusVehicles(){
        String depotName = "DepotName";
        int busCapacity = 5;
        Depot newDepot = new Depot(depotName, busCapacity);
        depotWrapper.validateAndCreateNewDepot(newDepot);
        for(int i = 0; i < busCapacity; i++){
            BusVehicle newBusVehicle = new BusVehicle("BUS-000-00" + i, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30, newDepot);
            busVehiculeWrapper.validateAndCreateNewBusVehicle(newBusVehicle);
        }

        List<Depot> retrievedDepots = depotRepository.findAll();

        Assert.assertEquals(1, retrievedDepots.size());
        Depot retrievedDepot = retrievedDepots.get(0);
        Assert.assertEquals(depotName, retrievedDepot.getName());
        Assert.assertEquals(5, retrievedDepot.getBusCapacity().intValue());
        Assert.assertEquals(5, retrievedDepot.getBusVehiclesParked().size());
    }

    @Test
    public void validateAndCreateNewDepotWithExistingBusVehicles(){
        String depotName = "DepotName";
        int busCapacity = 5;
        for(int i = 0; i < busCapacity; i++){
            BusVehicle newBusVehicle = new BusVehicle("BUS-000-00" + i, BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30);
            busVehiculeWrapper.validateAndCreateNewBusVehicle(newBusVehicle);
        }
        Depot newDepot = new Depot(depotName, busCapacity);
        newDepot.setBusVehiclesParked(busVehicleRepository.findAll());
        depotWrapper.validateAndCreateNewDepot(newDepot);

        List<Depot> retrievedDepots = depotRepository.findAll();

        Assert.assertEquals(1, retrievedDepots.size());
        Depot retrievedDepot = retrievedDepots.get(0);
        Assert.assertEquals(5, retrievedDepot.getBusVehiclesParked().size());
        List<BusVehicle> busVehicles = busVehicleRepository.findAll();
        Assert.assertEquals(busVehicles.get(0).getDepotParkedIn().getName(), retrievedDepot.getName());
    }

    @Test
    public void validateAndCreateNewDepotWithSameName(){
        String depotName = "DepotName";
        Depot newDepot = new Depot(depotName, 5);
        depotWrapper.validateAndCreateNewDepot(newDepot);
        newDepot = new Depot(depotName, 10);
        depotWrapper.validateAndCreateNewDepot(newDepot);

        List<Depot> retrievedDepots = depotRepository.findAll();

        Assert.assertEquals(1, retrievedDepots.size());
    }

    @Test
    public void addNewBusToDepot(){
        Depot depot = testHelper.createDepotWithBusVehicles("DepotName", 0, 5);
        depotWrapper.addNewBusVehicleToDepot(depot, new BusVehicle("BUS-000-000", BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30));
        List<Depot> retrievedDepots = depotRepository.findAll();
        Assert.assertEquals(1, retrievedDepots.get(0).getBusVehiclesParked().size());
    }

    @Test
    public void addNewBusToDepotOutsideLimit(){
        Depot depot = testHelper.createDepotWithBusVehicles("DepotName", 5);
        depotWrapper.addNewBusVehicleToDepot(depot, new BusVehicle(testHelper.generateNewBusVehiclePlateNumber(), BusVehicleType.REGULAR, BusVehicleColor.GREEN, 30));
        List<Depot> retrievedDepots = depotRepository.findAll();
        Assert.assertEquals(5, retrievedDepots.get(0).getBusVehiclesParked().size());
        List<BusVehicle> retrievedBusVehicule = busVehicleRepository.findAll();
        Assert.assertEquals(5, retrievedBusVehicule.size());
    }

    @Test
    public void validateAndCreateNewDepotNegativeCapacity(){
        String depotName = "DepotName";
        Depot newDepot = new Depot(depotName, -1);
        depotWrapper.validateAndCreateNewDepot(newDepot);

        List<Depot> retrievedDepots = depotRepository.findAll();

        Assert.assertEquals(0, retrievedDepots.size());
    }

    @Test
    public void validateAndCreateNewDepot0Capacity(){
        String depotName = "DepotName";
        Depot newDepot = new Depot(depotName, 0);
        depotWrapper.validateAndCreateNewDepot(newDepot);

        List<Depot> retrievedDepots = depotRepository.findAll();

        Assert.assertEquals(0, retrievedDepots.size());
    }

    @Test
    public void validateUpdateDepot(){
        String depotNameStart = "DepotNameStart";
        testHelper.createDepotWithBusVehicles(depotNameStart, 5);

        Depot updatedDepot = depotRepository.findByName(depotNameStart);
        String depotNameUpdated = "DepotNameUpdated";
        updatedDepot.setName(depotNameUpdated);
        depotWrapper.validateAndCreateNewDepot(updatedDepot);

        List<Depot> depots = depotRepository.findAll();
        Assert.assertEquals(1, depots.size());
        Assert.assertEquals(depotNameUpdated, depots.get(0).getName());
        Assert.assertEquals(5, depots.get(0).getBusVehiclesParked().size());

    }

    @Test
    public void createMultipleDepot(){
        String depotName = "DepotName";
        Depot newDepot = new Depot(depotName, 15);
        depotWrapper.validateAndCreateNewDepot(newDepot);

        String depotName2 = "DepotName2";
        Depot updatedDepot = new Depot(depotName2, 15);
        depotWrapper.validateAndCreateNewDepot(updatedDepot);

        List<Depot> depots = depotRepository.findAll();
        Assert.assertEquals(2, depots.size());
    }

    @Test
    public void testCreateNewDepotWithSameNameDoesNotReplace(){
        String depotName = "Depot1";
        testHelper.createDepotWithBusVehicles(depotName, 5);
        long firstId = depotRepository.findAll().get(0).getId();

        Depot depot = new Depot(depotName, 5);
        depotWrapper.validateAndCreateNewDepot(depot);
        List<Depot> depotsAfter = depotRepository.findAll();
        Assert.assertEquals(1, depotsAfter.size());
        long secondId = depotsAfter.get(0).getId();
        Assert.assertEquals(firstId, secondId);
    }
}
