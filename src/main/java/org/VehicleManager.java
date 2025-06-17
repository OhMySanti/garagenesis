package org;

import org.services.Service;
import org.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.AppState.save;

public class VehicleManager {

    public static String currency = "crystal coin";

    private static Vehicle lastRemovedFromActive = null;
    private static Vehicle lastRemovedFromArchive = null;

    private static Vehicle selectedVehicle;
    private static Service selectedService;

    public static Vehicle getLastRemovedFromActive() {
        return lastRemovedFromActive;
    }

    public static void setLastRemovedFromActive(Vehicle lastRemovedFromActive) {
        VehicleManager.lastRemovedFromActive = lastRemovedFromActive;
    }

    public static Vehicle getLastRemovedFromArchive() {
        return lastRemovedFromArchive;
    }

    public static void setLastRemovedFromArchive(Vehicle lastRemovedFromArchive) {
        VehicleManager.lastRemovedFromArchive = lastRemovedFromArchive;
    }

    public static Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    public static void setSelectedVehicle(Vehicle selectedVehicle) {
        VehicleManager.selectedVehicle = selectedVehicle;
    }

    public static Service getSelectedService() {
        return selectedService;
    }

    public static void setSelectedService(Service selectedService) {
        VehicleManager.selectedService = selectedService;
    }

    public static void completeAllServices(Vehicle vehicle) {
        List<Service> iterList = new ArrayList<>(vehicle.activeServices);
        for (Service service : iterList) {
            service.complete();
        }
    }

    public static String removeVehicle(AppState appState, Vehicle vehicle) {
        if (appState.getActiveVehiclesList().remove(vehicle)) {
            return "ACTIVE";
        } else if (appState.getArchivedVehiclesList().remove(vehicle)) {
            return "ARCHIVED";
        } else {
            return "ERROR?!";
        }
    }

    public static boolean moveVehicleToArchive(AppState appState, Vehicle vehicle) {
        if (appState.getActiveVehiclesList().remove(vehicle)) {
            appState.getArchivedVehiclesList().add(vehicle);
            vehicle.status = org.vehicles.EStatus.Status.ARCHIVED;
            return true;
        } else {
            return false;
        }
    }
}
