package com.grazzini.modelview;

import com.grazzini.model.BusVehicleColor;
import com.grazzini.model.BusVehicleType;

public class BusVehicleCreateFormModelView {
    private String plateNumber;

    private BusVehicleType type;

    private BusVehicleColor color;

    private Integer passengerCapacity;

    private String depotParkedInName;

    public String getPlateNumber() {
        return plateNumber;
    }

    public BusVehicleType getType() {
        return type;
    }

    public BusVehicleColor getColor() {
        return color;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public String getDepotParkedInName() {
        return depotParkedInName;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setType(BusVehicleType type) {
        this.type = type;
    }

    public void setColor(BusVehicleColor color) {
        this.color = color;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void setDepotParkedInName(String depotParkedInName) {
        this.depotParkedInName = depotParkedInName;
    }
}
