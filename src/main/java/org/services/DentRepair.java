package org.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.vehicles.Amphibian;
import org.vehicles.Car;
import org.vehicles.Ship;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.vehicles.Vehicle;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentRepair implements Service {

    String displayName = "Dent repair";

    Integer baseCost = 100;
    Vehicle vehicle;
    Long id;

    public DentRepair(){
    }

    public DentRepair(Vehicle vehicle){
        this.vehicle = vehicle;
        this.id = IdCounter.idCounterService.incrementAndGet();
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
