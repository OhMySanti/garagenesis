package org.services;

import org.vehicles.*;

public class PaintRetouch implements Service {

    String displayName = "Paint retouch";
    Integer baseCost = 200;
    Vehicle vehicle;

    public PaintRetouch(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Override
    public boolean isLegalService() {
        return true;
    }

    @Override
    public Integer calculateFee() {
        double multiplier = switch (this.vehicle.paintType){
            case BASIC -> 1;
            case MATTE -> 1.3;
            case METALLIC -> 1.6;
//            case UNSPECIFIED, OTHER ->
//                    throw new IllegalArgumentException("Specify vehicle's paint type before assigning this service!");
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
