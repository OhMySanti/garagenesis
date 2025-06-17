package org.vehicles;

public class Ship extends Vehicle {

    public Ship(String registration, ESize.Size size, EPaintType.PaintType paintType, VehicleOwner owner) {
        this.registration = registration;
        this.owner = owner;
        this.size = size;
        this.paintType = paintType;

        this.type = EVehicleType.VehicleType.SHIP;
        this.noOfWheels = 0;
    }

}
