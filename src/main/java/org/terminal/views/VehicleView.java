package org.terminal.views;

import org.AppState;
import org.VehicleManager;
import org.terminal.SelectionOptions;
import org.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.SelectionOptions.*;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class VehicleView {

    public static void vehicleView(AppState appState, Vehicle vehicle) {
        VehicleManager.setSelectedVehicle(vehicle);
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(vehicle));

        List<SelectionOptions.SelectionOption> vehicleViewOptions = new ArrayList<>();


        SelectionOption changeVehicleStatusOption = new SelectionOption(
                "U",
                "Update vehicle status",
                _ -> VehicleChangeStatusView.vehicleChangeStatusView(appState));

        SelectionOption vehicleServicesOption = new SelectionOption(
                "S",
                "Services",
                _ -> VehicleServicesView.vehicleActiveServicesView(appState));

        SelectionOption moveActiveVehicleToArchiveOption = new SelectionOption(
                "M",
                "Complete all services and move vehicle to archive",
                _ -> {
                    VehicleManager.completeAllServices(VehicleManager.getSelectedVehicle());
                    VehicleManager.moveVehicleToArchive(appState, vehicle);
                    ArchivedVehiclesListView.archivedVehiclesListView(appState);
                }
        );

        SelectionOption paymentStatusViewOption = new SelectionOption(
                "P",
                "Payments",
                _ -> VehiclePaymentsView.vehiclePaymentsView(appState, VehicleManager.getSelectedVehicle()));

        SelectionOption removeVehicleOption = new SelectionOption(
                "X",
                "Remove vehicle",
                _ -> {
                    System.out.println("Vehicle removed from " + VehicleManager.removeVehicle(appState, vehicle) + " list!");
                    MainView.mainView(appState);
                }
        );

        SelectionOption activeVehiclesSelectionOption = new SelectionOption(
                "B",
                "Back to Active Vehicles Menu",
                _ -> ActiveVehiclesListView.activeVehiclesListView(appState));

        vehicleViewOptions.add(changeVehicleStatusOption);
        vehicleViewOptions.add(vehicleServicesOption);
        vehicleViewOptions.add(paymentStatusViewOption);
        vehicleViewOptions.add(moveActiveVehicleToArchiveOption);
        vehicleViewOptions.add(removeVehicleOption);
        vehicleViewOptions.add(activeVehiclesSelectionOption);

        displayAvailableOptions(vehicleViewOptions);
        userChooseFromOptions(vehicleViewOptions);
    }

}
