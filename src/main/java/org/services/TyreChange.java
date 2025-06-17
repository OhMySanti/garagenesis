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
public class TyreChange implements Service {

    String displayName = "Tyre change";
    Integer baseCost = 25;
    Vehicle vehicle;
    Long id;

    public TyreChange(Vehicle vehicle){
        this.vehicle = vehicle;
        this.id = IdCounter.idCounterService.incrementAndGet();
    }

    public TyreChange() {

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
