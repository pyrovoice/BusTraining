package com.grazzini.mainpage;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainPage {

    @Autowired
    BusVehicleRepository busVehiculeRepository;
    @Autowired
    DepotRepository depotRepository;

    @GetMapping("/")
    public String mainPageDisplay(Model model) {
        model.addAttribute("Depots", depotRepository.findAll());
        model.addAttribute("BusVehiculesWithoutDepot", busVehiculeRepository.findByDepotParkedInNull());
        return "mainpage";
    }

    @GetMapping("/createbusvehicle")
    public String createBusVehicleDisplay(@ModelAttribute("busVehicle") BusVehicle b) {
        return "createBusVehicle";
    }

    @PostMapping("/createbusvehicle")
    public String checkAndCreateBusVehicle (@Valid BusVehicle newBusVehicle, BindingResult bindingResultModel, Model model) {
        busVehiculeRepository.save(newBusVehicle);
        return "mainpage";
    }

}
