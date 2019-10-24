package com.grazzini.modelview;

import com.grazzini.model.Depot;

import java.util.ArrayList;
import java.util.List;

public class DepotModelView {
    List<Long> busVehicleIds = new ArrayList<>();
    Long depotId;
    String depotName;
    Integer depotCapacity;

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public List<Long> getBusVehicleIds() {
        return busVehicleIds;
    }

    public void setBusVehicleIds(List<Long> busVehicleIds) {
        this.busVehicleIds = busVehicleIds;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public Integer getDepotCapacity() {
        return depotCapacity;
    }

    public void setDepotCapacity(Integer depotCapacity) {
        this.depotCapacity = depotCapacity;
    }
}
