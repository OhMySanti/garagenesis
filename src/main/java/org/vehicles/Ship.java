package org.vehicles;

public class Ship extends Vehicle {

    public Ship(String registration, Size size, PaintType paintType, VehicleOwner owner) {
        this.registration = registration;
        this.owner = owner;
        this.size = size;
        this.paintType = paintType;

        this.type = "Ship\t";
        this.noOfWheels = 0;
    }

}
