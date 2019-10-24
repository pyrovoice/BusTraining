package com.grazzini.views;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.wrapper.BusVehiculeWrapper;
import com.grazzini.wrapper.DepotWrapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class used to provide all tools that other test classes will use. All test classes should extend this class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTestClass {
    @Autowired protected BusVehiculeWrapper busVehiculeWrapper;
    @Autowired protected BusVehicleRepository busVehicleRepository;
    @Autowired protected DepotWrapper depotWrapper;
    @Autowired protected DepotRepository depotRepository;

    protected TestHelper testHelper;

    @Before
    public void initHelperAndCleanDatabase(){
        testHelper = new TestHelper(busVehicleRepository, depotRepository, busVehiculeWrapper, depotWrapper);
        testHelper.cleanDatabase();
    }
}
