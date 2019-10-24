package com.grazzini.views;

import com.grazzini.dao.BusVehicleRepository;
import com.grazzini.dao.DepotRepository;
import com.grazzini.model.BusVehicle;
import com.grazzini.model.Depot;
import com.grazzini.modelview.DepotModelView;
import com.grazzini.wrapper.DepotModelViewWrapper;
import com.grazzini.wrapper.DepotWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CreateOrUpdateDepotPage {

    private static final String DEPOT_PAGE_ADRESS = "/createorupdatedepot";
    private static final String DEPOT_PAGE_HTML = "createOrUpdateDepot";

    @Autowired
    DepotWrapper depotWrapper;
    @Autowired
    BusVehicleRepository busVehicleRepository;
    @Autowired
    DepotRepository depotRepository;
    @Autowired
    DepotModelViewWrapper depotModelViewWrapper;

    @GetMapping(DEPOT_PAGE_ADRESS)
    public String createBusVehicleDisplay(DepotModelView depotModelView, Model model) {
        model.addAttribute("BusVehicles", busVehicleRepository.findAll());
        return DEPOT_PAGE_HTML;
    }

    @GetMapping(DEPOT_PAGE_ADRESS + "/{id}")
    public String createBusVehicleDisplay(Model model, @PathVariable(value = "id") long id, HttpServletResponse response) throws IOException {
        Depot depot = depotRepository.findById(id).get();
        if(depot == null){
            response.sendRedirect(DEPOT_PAGE_ADRESS);
            return null;
        }
        model.addAttribute("depotID", id);
        model.addAttribute("depotName", depot.getName());
        model.addAttribute("depotCapacity", depot.getBusCapacity());
        model.addAttribute("BusVehicles", busVehicleRepository.findAll());
        return DEPOT_PAGE_HTML;
    }

    @PostMapping(DEPOT_PAGE_ADRESS)
    public String checkAndCreateDepot (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String depotName = request.getParameter("depotName");
        String depotCapacity = request.getParameter("depotCapacity");
        String depotId = request.getParameter("depotID");
        String[] busVehicleSelectedIds = request.getParameterValues("busVehicleIds");

        Depot itemSuccesfullyPersisted = depotWrapper.validateAndCreateNewDepot(depotId, depotName, depotCapacity, busVehicleSelectedIds);

        if(itemSuccesfullyPersisted == null){
            return DEPOT_PAGE_ADRESS;
        }

        response.sendRedirect("/");
        return null;
    }
}
