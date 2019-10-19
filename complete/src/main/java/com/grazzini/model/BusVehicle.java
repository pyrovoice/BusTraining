package com.grazzini.model;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name="BUSVEHICULE")
public class BusVehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,name = "BUSVEHICULE_PLATENUMBER")
    private String plateNumber;

    @Column(name = "BUSVEHICULE_TYPE")
    private BusVehicleType type;

    @Column(name = "BUSVEHICULE_COLOR")
    private BusVehicleColor color;

    @Column(name = "BUSVEHICULE_PASSENGERCAPACITY")
    private Integer passengerCapacity;

    @ManyToOne
    @JoinColumn(name ="FK_depotId")
    private Depot depotParkedIn;

    public BusVehicle(String plateNumber, BusVehicleType type, BusVehicleColor color, Integer passengerCapacity) {
        this(plateNumber, type, color, passengerCapacity, null);
    }

    public BusVehicle(String plateNumber, BusVehicleType type, BusVehicleColor color, Integer passengerCapacity, Depot depot) {
        this.plateNumber = plateNumber;
        this.type = type;
        this.color = color;
        this.passengerCapacity = passengerCapacity;
        this.depotParkedIn = depot;
    }

    public BusVehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BusVehicleType getType() {
        return type;
    }

    public void setType(BusVehicleType type) {
        this.type = type;
    }

    public BusVehicleColor getColor() {
        return color;
    }

    public void setColor(BusVehicleColor color) {
        this.color = color;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Depot getDepotParkedIn() {
        return depotParkedIn;
    }

    public void setDepotParkedIn(Depot depotParkedIn) {
        this.depotParkedIn = depotParkedIn;
    }
}



