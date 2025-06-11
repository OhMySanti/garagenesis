package org.services;

import org.vehicles.Vehicle;


// TODO consider the alternative design:

class Services {
    public record Service(String displayName, Integer fee){}

    static Service PAINT_RETOUCH = new Service("Paint Retouch", 1000);
    static Service ENGINE_DIAGNOSTICS = new Service("Engine Diagnostics", 500);
    static Service CLEANING = new Service("Cleaning", 100);
}

// TODO: what are pros and cons?

public interface Service {
    Integer calculateFee();

    boolean isLegalService();

    String getDisplayName();

    Vehicle getVehicle();
}
