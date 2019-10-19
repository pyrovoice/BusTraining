package com.grazzini.views;

import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.Depot;
import com.grazzini.modelview.BusVehicleCreateFormModelView;
import com.grazzini.wrapper.BusVehiculeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CreateBusVehiclePage {

    @Autowired
    DepotRepository depotRepository;
    @Autowired
    BusVehiculeWrapper busVehiculeWrapper;

    @GetMapping("/createbusvehicle")
    public String createBusVehicleDisplay(BusVehicleCreateFormModelView busVehicleCreateFormModelView, Model model) {
        List<Depot> depotsWithAvailableSpace = depotRepository.findAllDepotAvailableSpace();
        model.addAttribute("Depots", depotsWithAvailableSpace);
        return "createBusVehicle";
    }

    @PostMapping("/createbusvehicle")
    public String checkAndCreateBusVehicle (BusVehicleCreateFormModelView busVehicleCreateFormModelView, HttpServletResponse response) throws IOException {
        BusVehicle newBusVehicle = getBusVehicleFromModelView(busVehicleCreateFormModelView);
        BusVehicle itemSuccessfullyPersisted = busVehiculeWrapper.validateAndCreateNewBusVehicle(newBusVehicle);
        if(itemSuccessfullyPersisted == null){
            return "createBusVehicle";
        }
        response.sendRedirect("/");
        return null;
    }

    private BusVehicle getBusVehicleFromModelView(BusVehicleCreateFormModelView nBusVehicle) {

        Depot depotParkedIn = depotRepository.findByName(nBusVehicle.getDepotParkedInName());
        return new BusVehicle(nBusVehicle.getPlateNumber(), nBusVehicle.getType(), nBusVehicle.getColor(), nBusVehicle.getPassengerCapacity(), depotParkedIn);
    }
}
