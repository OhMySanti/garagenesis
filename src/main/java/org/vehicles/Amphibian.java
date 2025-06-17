package org.vehicles;

public class Amphibian extends Vehicle {

    public Amphibian(String registration, ESize.Size size, EPaintType.PaintType paintType, VehicleOwner owner) {
        this.registration = registration;
        this.owner = owner;
        this.size = size;
        this.paintType = paintType;

        this.type = EVehicleType.VehicleType.AMPHIBIAN;
        this.noOfWheels = 6;
    }

}
