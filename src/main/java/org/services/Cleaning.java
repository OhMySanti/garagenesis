package org.services;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.vehicles.Vehicle;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cleaning implements Service {

    public static String displayName = "Cleaning";

    Integer baseCost = 50;
    Vehicle vehicle;
    Long id;

    public Cleaning(Vehicle vehicle){
        this.vehicle = vehicle;
        this.id = IdCounter.idCounterService.incrementAndGet();
    }

    public Cleaning(){

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

    @Override
    public boolean remove(){
        return this.getVehicle().activeServices.remove(this);
    }

    @Override
    public boolean complete(){
        if (this.vehicle.activeServices.remove(this)){
            this.vehicle.duePayment += this.calculateFee();
            this.vehicle.historyOfServices.add(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
