package org.vehicles;

public class Car extends Vehicle {

    public Car(String registration, ESize.Size size, EPaintType.PaintType paintType, VehicleOwner owner) {
        this.registration = registration;
        this.owner = owner;
        this.size = size;
        this.paintType = paintType;

        this.type = EVehicleType.VehicleType.CAR;
        this.noOfWheels = 4;


    }

}
