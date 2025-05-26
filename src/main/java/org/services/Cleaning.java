package org.services;

import org.vehicles.Vehicle;

public class Cleaning implements Service {

    public static String displayName = "Cleaning";

    Integer baseCost = 50;
    Vehicle vehicle;

    public Cleaning(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public boolean isLegalService() {
        return true;
    }

    @Override
    public Integer calculateFee() {
        double multiplier = switch (this.vehicle.size) {
            case SMALL -> 1;
            case MEDIUM -> 1.2;
            case LARGE -> 1.5;
//            case UNSPECIFIED ->
//                    throw new IllegalArgumentException("Specify vehicle's size before assigning this service!");
        };
        return (int) (multiplier * baseCost);
    }

    @Override
    public String getDisplayName(){
        return displayName;
    }

    @Override
    public Vehicle getVehicle(){
        return vehicle;
    }
}
