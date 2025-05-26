package org.vehicles;

public class Car extends Vehicle {

    public Car(String registration, Size size, PaintType paintType, VehicleOwner owner) {
        this.registration = registration;
        this.owner = owner;
        this.size = size;
        this.paintType = paintType;

        this.type = "Car\t";
        this.noOfWheels = 4;

    }

}
