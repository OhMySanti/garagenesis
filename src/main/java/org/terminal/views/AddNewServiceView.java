package org.terminal.views;

import org.AppState;
import org.VehicleManager;
import org.services.*;
import org.terminal.SelectionOptions;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class AddNewServiceView {
    public static void addNewServiceView(AppState appState) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(VehicleManager.getSelectedVehicle()));
        System.out.println("ACTIVE SERVICES: " + VehicleManager.getSelectedVehicle().activeServices.size());

        List<SelectionOptions.SelectionOption> addNewServiceViewOptions = addNewServiceViewSelection(appState);

        displayAvailableOptions(addNewServiceViewOptions);
        userChooseFromOptions(addNewServiceViewOptions);
    }

    static List<SelectionOptions.SelectionOption> addNewServiceViewSelection(AppState appState) {
        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();

        SelectionOptions.SelectionOption addServiceCleaning = new SelectionOptions.SelectionOption(
                "1",
                "Cleaning",
                _ -> {
                    Service service = (new Cleaning(VehicleManager.getSelectedVehicle()));
                    if (service.isLegalService()) {
                        VehicleManager.getSelectedVehicle().activeServices.add(service);
                        VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                    } else {
                        System.out.println("Service can't be applied to this vehicle");
                    }
                });

        SelectionOptions.SelectionOption addServiceDentRepair = new SelectionOptions.SelectionOption(
                "2",
                "Dent repair",
                _ -> {
                    Service service = (new DentRepair(VehicleManager.getSelectedVehicle()));
                    if (service.isLegalService()) {
                        VehicleManager.getSelectedVehicle().activeServices.add(service);
                        VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                    } else {
                        System.out.println("Service can't be applied to this vehicle");
                    }
                });

        SelectionOptions.SelectionOption addServiceEngineDiagnostics = new SelectionOptions.SelectionOption(
                "3",
                "Engine diagnostics",
                _ -> {
                    Service service = (new EngineDiagnostics(VehicleManager.getSelectedVehicle()));
                    if (service.isLegalService()) {
                        VehicleManager.getSelectedVehicle().activeServices.add(service);
                        VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                    } else {
                        System.out.println("Service can't be applied to this vehicle");
                    }
                });

        SelectionOptions.SelectionOption addServicePaintRetouch = new SelectionOptions.SelectionOption(
                "4",
                "Paint retouch",
                _ -> {
                    Service service = (new PaintRetouch(VehicleManager.getSelectedVehicle()));
                    if (service.isLegalService()) {
                        VehicleManager.getSelectedVehicle().activeServices.add(service);
                        VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                    } else {
                        System.out.println("Service can't be applied to this vehicle");
                    }
                });

        SelectionOptions.SelectionOption addServiceTyreChange = new SelectionOptions.SelectionOption(
                "5",
                "Tyre change",
                _ -> {
                    Service service = (new TyreChange(VehicleManager.getSelectedVehicle()));
                    if (service.isLegalService()) {
                        VehicleManager.getSelectedVehicle().activeServices.add(service);
                        VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
                    } else {
                        System.out.println("Service can't be applied to this vehicle");
                    }
                });

        SelectionOptions.SelectionOption returnToSelectedVehicleViewSelectionOption = new SelectionOptions.SelectionOption(
                "B",
                "Back to vehicle",
                _ -> VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle()));

        optionsList.add(addServiceCleaning);
        optionsList.add(addServiceDentRepair);
        optionsList.add(addServiceEngineDiagnostics);
        optionsList.add(addServicePaintRetouch);
        optionsList.add(addServiceTyreChange);

        optionsList.add(returnToSelectedVehicleViewSelectionOption);

        return optionsList;
    }

}
