package org.services;

import org.vehicles.Amphibian;
import org.vehicles.Car;
import org.vehicles.Ship;
import org.vehicles.Vehicle;

public class DentRepair implements Service {

    String displayName = "Dent repair";

    Integer baseCost = 100;
    Vehicle vehicle;

    public DentRepair(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public boolean isLegalService() {
        return true;
    }

    @Override
    public Integer calculateFee() {
        double multiplier = switch (this.vehicle) {
            case Car car -> 1;
            case Amphibian amphibian -> 1.5;
            case Ship ship -> 2;
            case null, default ->
                    throw new IllegalArgumentException("Specify vehicle's type before assigning this service!");
        };

        return (int) (multiplier * baseCost);
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
