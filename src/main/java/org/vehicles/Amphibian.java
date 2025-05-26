package org.vehicles;

public class Amphibian extends Vehicle {

    public Amphibian(String registration, Size size, PaintType paintType, VehicleOwner owner) {
        this.registration = registration;
        this.owner = owner;
        this.size = size;
        this.paintType = paintType;

        this.type = "Amphibian";
        this.noOfWheels = 6;
    }

}
