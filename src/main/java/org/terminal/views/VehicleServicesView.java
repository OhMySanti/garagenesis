package org.terminal.views;

import org.AppState;
import org.VehicleManager;
import org.services.Service;
import org.terminal.SelectionOptions;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.views.AddNewServiceView.addNewServiceView;
import static org.terminal.views.SelectedServiceView.selectedServiceView;
import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class VehicleServicesView {
    public static void vehicleActiveServicesView(AppState appState) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(VehicleManager.getSelectedVehicle()));
        org.terminal.DisplayVehicles.displayArchivedServicesLists(VehicleManager.getSelectedVehicle());

        System.out.println("ACTIVE SERVICES: " + VehicleManager.getSelectedVehicle().activeServices.size());

        List<SelectionOptions.SelectionOption> optionsList = buildActiveServicesListViewSelection(appState);

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);
    }

    static List<SelectionOptions.SelectionOption> buildActiveServicesListViewSelection(AppState appState) {
        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();

        int selector = 1;
        for (Service service : VehicleManager.getSelectedVehicle().activeServices) {
            optionsList.add(new SelectionOptions.SelectionOption(
                    Integer.toString(selector),
                    service.getDisplayName(),
                    _ -> {
                        VehicleManager.setSelectedService(service);
                        selectedServiceView(appState, VehicleManager.getSelectedService());
                    }));
            selector++;
        }

        SelectionOptions.SelectionOption addNewServiceSelectionOption = new SelectionOptions.SelectionOption(
                "N",
                "Add new service",
                _ -> addNewServiceView(appState));

        SelectionOptions.SelectionOption returnToSelectedVehicleViewSelectionOption = new SelectionOptions.SelectionOption(
                "B",
                "Back to vehicle",
                _ -> VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle()));

        optionsList.add(addNewServiceSelectionOption);
        optionsList.add(returnToSelectedVehicleViewSelectionOption);

        return optionsList;
    }


}
