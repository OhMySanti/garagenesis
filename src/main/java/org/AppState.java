package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.vehicles.Vehicle;

public class AppState {
    public static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
    public static final File SAVE_FILE = new File("app-state.json");

    private List<Vehicle> activeVehiclesList = new ArrayList<>();
    private List<Vehicle> archivedVehiclesList = new ArrayList<>();

    private long serviceIdCounter = 0;
    private long vehicleIdCounter = 0;

    public AppState() {
    }

    public static void save(AppState state) {
        try {
            state.setVehicleIdCounter(Vehicle.getIdCounterVehicle());
            state.setServiceIdCounter(org.services.IdCounter.getIdCounterService());
            mapper.writeValue(SAVE_FILE, state);
            System.out.println("AppState saved.");
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
        }
    }

    public List<Vehicle> getActiveVehiclesList() {
        return activeVehiclesList;
    }

    public void setActiveVehiclesList(List<Vehicle> activeVehiclesList) {
        this.activeVehiclesList = activeVehiclesList;
    }

    public List<Vehicle> getArchivedVehiclesList() {
        return archivedVehiclesList;
    }

    public void setArchivedVehiclesList(List<Vehicle> archivedVehiclesList) {
        this.archivedVehiclesList = archivedVehiclesList;
    }

    void save() {
        save(this);
    }

    public static AppState load() {
        if (!SAVE_FILE.exists()) {
            System.out.println("No save file found. Creating new AppState.");
            AppState newState = new AppState();
            save(newState);
            return newState;
        }

        try {
            AppState state = mapper.readValue(SAVE_FILE, AppState.class);
            Vehicle.setIdCounterVehicle(state.getVehicleIdCounter());
            org.services.IdCounter.setIdCounterService(state.getServiceIdCounter());
            return state;
        } catch (IOException e) {
            System.out.println("Load failed. Creating new AppState");
            System.out.println(e.getMessage());
            return new AppState();
        }
    }

    public long getServiceIdCounter() {
        return serviceIdCounter;
    }

    public void setServiceIdCounter(long serviceIdCounter) {
        this.serviceIdCounter = serviceIdCounter;
    }

    public long getVehicleIdCounter() {
        return vehicleIdCounter;
    }

    public void setVehicleIdCounter(long vehicleIdCounter) {
        this.vehicleIdCounter = vehicleIdCounter;
    }
}
