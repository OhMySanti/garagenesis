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
public class EngineDiagnostics implements Service {

    String displayName = "Engine diagnostics";
    Integer baseCost = 150;
    Vehicle vehicle;
    Long id;

    public EngineDiagnostics(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.id = IdCounter.idCounterService.incrementAndGet();
    }

    public EngineDiagnostics() {

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
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public boolean remove() {
        return this.getVehicle().activeServices.remove(this);
    }

    @Override
    public boolean complete() {
        if (this.vehicle.activeServices.remove(this)) {
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
