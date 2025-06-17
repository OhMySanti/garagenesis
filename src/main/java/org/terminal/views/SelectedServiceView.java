package org.terminal.views;

import org.AppState;
import org.VehicleManager;
import org.services.Service;
import org.terminal.SelectionOptions;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class SelectedServiceView {
    public static void selectedServiceView(AppState appState, Service service) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(VehicleManager.getSelectedVehicle()));
        System.out.println("SELECTED Service:");
        System.out.println(service.getDisplayName());

        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();

        SelectionOptions.SelectionOption completeServiceSelectionOption = new SelectionOptions.SelectionOption(
                "C",
                "Mark service as completed and move to vehicle's history",
                _ -> {
                    VehicleManager.getSelectedService().complete();
                    VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                });

        SelectionOptions.SelectionOption removeServiceSelectionOption = new SelectionOptions.SelectionOption(
                "X",
                "Remove selected service",
                _ -> {
                    VehicleManager.getSelectedService().remove();
                    VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                });

        SelectionOptions.SelectionOption backToVehicleViewSelectionOption = new SelectionOptions.SelectionOption(
                "B",
                "Back to vehicle",
                _ -> VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle()));

        optionsList.add(completeServiceSelectionOption);
        optionsList.add(removeServiceSelectionOption);
        optionsList.add(backToVehicleViewSelectionOption);

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);
    }


}
