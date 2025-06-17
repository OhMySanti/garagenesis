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
public class PaintRetouch implements Service {

    String displayName = "Paint retouch";
    Integer baseCost = 200;
    Vehicle vehicle;
    Long id;

    public PaintRetouch(Vehicle vehicle){
        this.vehicle = vehicle;
        this.id = IdCounter.idCounterService.incrementAndGet();
    }

    public PaintRetouch() {

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
