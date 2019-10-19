package com.grazzini.views;

import com.grazzini.model.Depot;
import com.grazzini.wrapper.DepotWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CreateDepotPage {

    @Autowired
    DepotWrapper depotWrapper;

    @GetMapping("/createdepot")
    public String createBusVehicleDisplay(Depot depot) {
        return "createDepot";
    }

    @PostMapping("/createdepot")
    public String checkAndCreateDepot (Depot newDepot, HttpServletResponse response) throws IOException {
        Depot itemSuccesfullyPersisted = depotWrapper.validateAndCreateNewDepot(newDepot);
        if(itemSuccesfullyPersisted == null){
            return "createDepot";
        }
        response.sendRedirect("/");
        return null;
    }
}
