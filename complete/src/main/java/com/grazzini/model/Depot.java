package com.grazzini.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity(name="DEPOT")
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "DEPOT_NAME")
    private String name;

    @NotNull
    @Column(name = "DEPOT_BUSCAPACITY")
    private Integer busCapacity;

    @Column(name = "DEPOT_BUSVEHICULESPARKED")
    @OneToMany(mappedBy="depotParkedIn", cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
    private List<BusVehicle> busVehiclesParked = new ArrayList<>();


    /*public Set<BusVehicle> getBusVehicles(){
        return this.busVehiculesParked;
    }*/
    public Depot(String name, int busCapacity) {
        this(name, busCapacity, null);
    }

    public Depot(String name, int busCapacity, Collection<BusVehicle> busVehicles) {
        this.name = name;
        this.busCapacity = busCapacity;
        if(busVehicles != null){
            this.busVehiclesParked = new ArrayList<>(busVehicles);
        }
    }

    public Depot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBusCapacity() {
        return busCapacity;
    }

    public void setBusCapacity(int busCapacity) {
        this.busCapacity = busCapacity;
    }

    public List<BusVehicle> getBusVehiclesParked() {
        return busVehiclesParked;
    }

    public void setBusVehiclesParked(List<BusVehicle> busVehiclesParked) {
        this.busVehiclesParked = busVehiclesParked;
    }
}
