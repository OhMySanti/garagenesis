package org;

import org.services.Service;
import org.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.Menus.activeVehiclesListView;
import static org.terminal.Menus.archivedVehiclesListView;
import static org.terminal.UserInput.input;

public class VehicleManager {
    public static List<Vehicle> activeVehiclesList = new ArrayList<>(); // TODO: List<Vechice> ArrayList LIST LISTTT!!!
    public static List<Vehicle> archivedVehiclesList = new ArrayList<>();

    public static String currency = "crystal coin"; //

    static Vehicle lastRemovedFromActive = null; // TODO: looks ify, why do we have to remeber this ?
    static Vehicle lastRemovedFromArchive = null;

    public static void removeVehicleFromActive(Vehicle vehicle) {
        if (activeVehiclesList.contains(vehicle)) {
            activeVehiclesList.remove(vehicle);
            lastRemovedFromActive = vehicle;
            System.out.println("Vehicle removed!");
        } else {
            System.out.println("Something went very wrong... No such vehicle among active vehicles");
        }
        System.out.println("Press ENTER to continue");
        input.nextLine();

        activeVehiclesListView();
    }

    public void removeVehicleFromArchive(Vehicle vehicle) {
        if (archivedVehiclesList.contains(vehicle)) {
            archivedVehiclesList.remove(vehicle);
            lastRemovedFromArchive = vehicle;
            System.out.println("Vehicle removed from the archive!");
        } else {
            System.out.println("Something went very wrong...No such vehicle among archived vehicles");
        }
        System.out.println("Press ENTER to continue");
        input.nextLine();

        archivedVehiclesListView();
    }


    public static void moveVehicleToHistory(Vehicle vehicle) {
        if (activeVehiclesList.contains(vehicle)) {
            archivedVehiclesList.add(vehicle);
            activeVehiclesList.remove(vehicle);
            System.out.println("Vehicle " + vehicle.registration + " moved from active to archived vehicles");
        } else {
            System.out.println("Something went very wrong... No such vehicle among active vehicles");
        }
    }

    public static void completeActiveService(Service service) {
        service.getVehicle().duePayment += service.calculateFee();
        service.getVehicle().historyOfServices.add(service);
        service.getVehicle().activeServices.remove(service);

        System.out.println("Service " + service.getDisplayName() + " moved to vehicle history");
    }

}
