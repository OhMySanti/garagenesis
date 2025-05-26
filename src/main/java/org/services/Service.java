package org.services;

import org.vehicles.Vehicle;

public interface Service {
    Integer calculateFee();

    boolean isLegalService();

    String getDisplayName();

    Vehicle getVehicle();
}
