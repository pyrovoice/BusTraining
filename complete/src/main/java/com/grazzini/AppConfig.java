package com.grazzini;

import com.grazzini.modelview.BusVehicleCreateFormModelView;
import com.grazzini.wrapper.BusVehiculeWrapper;
import com.grazzini.wrapper.DepotWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BusVehiculeWrapper busVehiculeWrapper(){
        return new BusVehiculeWrapper();
    }
    @Bean
    public DepotWrapper depotWrapper(){
        return new DepotWrapper();
    }
    @Bean
    public BusVehicleCreateFormModelView busVehicleCreateFormModelView(){return new BusVehicleCreateFormModelView();}
}
