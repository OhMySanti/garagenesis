package org.vehicles;

public class VehicleFactory {

    public static Vehicle createVehicle(String vehicleType, String registration, ESize.Size size,
                                        EPaintType.PaintType paintType, VehicleOwner owner){
        return switch (vehicleType) {
            case "Car" -> new Car(registration, size, paintType, owner);
            case "Amphibian" -> new Amphibian(registration, size, paintType, owner);
            case "Ship" -> new Ship(registration, size, paintType, owner);
            default -> null;
        };
    }
}
