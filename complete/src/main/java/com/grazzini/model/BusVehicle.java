package com.grazzini.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name="BUSVEHICULE")
@Getter
@Setter
public class BusVehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,name = "BUSVEHICULE_PLATENUMBER")
    @NotBlank
    //Regex match BUS-XXX-XXX where X is any number
    @Pattern(regexp = "BUS-\\d{3}-\\d{3}")
    private String plateNumber;

    @NotNull
    @Column(name = "BUSVEHICULE_TYPE")
    private BusVehicleType type;

    @NotNull
    @Column(name = "BUSVEHICULE_COLOR")
    private BusVehicleColor color;

    @NotNull
    @Min(0)
    @Max(70)
    @Column(name = "BUSVEHICULE_PASSENGERCAPACITY")
    private int passengerCapacity;

    @ManyToOne
    @JoinColumn(name ="FK_depotId")
    private Depot depotParkedIn;

    public BusVehicle(@Pattern(regexp = "BUS-\\d{3}-\\d{3}") String plateNumber, BusVehicleType type, BusVehicleColor color, @Min(0) @Max(70) int passengerCapacity) {
        this.plateNumber = plateNumber;
        this.type = type;
        this.color = color;
        this.passengerCapacity = passengerCapacity;
    }
}



