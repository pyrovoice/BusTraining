package com.grazzini.views;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.Depot;
import com.grazzini.modelview.BusVehicleCreateFormModelView;
import com.grazzini.wrapper.BusVehiculeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CreateOrUpdateBusVehiclePage {

    @Autowired
    DepotRepository depotRepository;
    @Autowired
    BusVehiculeWrapper busVehiculeWrapper;
    @Autowired
    BusVehicleRepository busVehicleRepository;

    @GetMapping("/createorupdatebusvehicle")
    public String createBusVehicleDisplay(BusVehicleCreateFormModelView busVehicleCreateFormModelView, Model model) {
        List<Depot> depotsWithAvailableSpace = depotRepository.findAllDepotAvailableSpace();
        model.addAttribute("Depots", depotsWithAvailableSpace);
        return "createOrUpdateBusVehicle";
    }

    @GetMapping("/createorupdatebusvehicle/{id}")
    public String createBusVehicleDisplay(Model model, @PathVariable(value = "id") long id, HttpServletResponse response) throws IOException {
        BusVehicle busVehicle = busVehicleRepository.findById(id).get();
        if(busVehicle == null){
            response.sendRedirect("/createorupdatebusvehicle");
            return null;
        }

        model.addAttribute("busVehicleId", id);
        model.addAttribute("busVehiclePlateNumber", busVehicle.getPlateNumber());
        String depotParkedInName = busVehicle.getDepotParkedIn() != null ? busVehicle.getDepotParkedIn().getName() : "null";
        model.addAttribute("busVehicleDepotParkedIn", depotParkedInName);
        model.addAttribute("busVehiclePassengerCapacity", busVehicle.getPassengerCapacity());
        model.addAttribute("busVehicleColor", busVehicle.getColor().toString());
        model.addAttribute("busVehicleType", busVehicle.getType().toString());
        List<Depot> depotsWithAvailableSpace = depotRepository.findAllDepotAvailableSpace();
        model.addAttribute("Depots", depotsWithAvailableSpace);

        return "createOrUpdateBusVehicle";
    }

    @PostMapping("/createorupdatebusvehicle")
    public String checkAndCreateBusVehicle (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String busVehicleId = request.getParameter("busVehicleId");
        String busVehiclePlateNumber = request.getParameter("busVehiclePlateNumber");
        String busVehicleDepotParkedIn = request.getParameter("busVehicleDepotParkedIn");
        String busVehiclePassengerCapacity = request.getParameter("busVehiclePassengerCapacity");
        String busVehicleColor = request.getParameter("busVehicleColor");
        String busVehicleType = request.getParameter("busVehicleType");

        BusVehicle itemSuccesfullyPersisted = busVehiculeWrapper.validateAndCreateNewBusVehicle(busVehicleId, busVehiclePlateNumber, busVehiclePassengerCapacity, busVehicleColor, busVehicleType, busVehicleDepotParkedIn);

        if(itemSuccesfullyPersisted == null){
            return "/createorupdatebusvehicle";
        }

        response.sendRedirect("/");
        return null;
    }
}
