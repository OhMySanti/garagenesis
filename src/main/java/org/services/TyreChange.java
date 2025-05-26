package org.services;

import org.vehicles.Vehicle;

public class TyreChange implements Service {

    String displayName = "Tyre change";

    Integer baseCost = 25;

    Vehicle vehicle;

    public TyreChange(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public boolean isLegalService() {
        return this.vehicle.noOfWheels > 0;
    }

    @Override
    public Integer calculateFee() {
        if (this.vehicle.noOfWheels < 1) {
            throw new IllegalArgumentException("Can't change tyres on wheelless vehicle");
        } else {
            return this.vehicle.noOfWheels * baseCost;
        }
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
