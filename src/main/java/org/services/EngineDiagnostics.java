package org.services;

import org.vehicles.Vehicle;

public class EngineDiagnostics implements Service {

    String displayName = "Engine diagnostics";

    Integer baseCost = 150;

    Vehicle vehicle;

    public EngineDiagnostics(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public boolean isLegalService() {
        return true;
    }

    @Override
    public Integer calculateFee() {
        return baseCost;
    }

    @Override
    public String getDisplayName(){
        return this.displayName;
    }

    @Override
    public Vehicle getVehicle(){
        return vehicle;
    }
}
