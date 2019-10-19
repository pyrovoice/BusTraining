package com.grazzini.views;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainPage {

    @Autowired BusVehicleRepository busVehicleRepository;
    @Autowired DepotRepository depotRepository;

    @GetMapping("/")
    public String mainPageDisplay(Model model) {
        List<BusVehicle> busVehicleWithoutDepot = busVehicleRepository.findByDepotParkedInNull();
        model.addAttribute("Depots",  depotRepository.findAll());
        model.addAttribute("BusVehiclesWithoutDepot", busVehicleWithoutDepot);
        return "mainpage";
    }




}
