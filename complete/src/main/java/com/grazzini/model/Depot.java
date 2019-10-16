package com.grazzini.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name="DEPOT")
@Getter
@Setter
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "DEPOT_NAME")
    private String name;

    @NotNull
    @Column(name = "DEPOT_BUSCAPACITY")
    private int busCapacity;

    @Column(name = "DEPOT_BUSVEHICULESPARKED")
    @OneToMany(mappedBy="id", cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
    private Set<BusVehicle> busVehiclesParked = new HashSet<BusVehicle>();


    /*public Set<BusVehicle> getBusVehicles(){
        return this.busVehiculesParked;
    }*/

    public Depot(@NotBlank String name, @NotNull int busCapacity, Collection<BusVehicle> busVehicles) {
        this.name = name;
        this.busCapacity = busCapacity;
        this.busVehiclesParked = new HashSet<BusVehicle>(busVehicles);
    }
}
