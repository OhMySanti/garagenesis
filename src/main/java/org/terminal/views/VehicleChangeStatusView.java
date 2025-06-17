package org.terminal.views;

import org.AppState;
import org.VehicleManager;
import org.terminal.SelectionOptions;
import org.vehicles.EStatus;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class VehicleChangeStatusView {
    public static void vehicleChangeStatusView(AppState appState) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(VehicleManager.getSelectedVehicle()));
        System.out.println("STATUS: " + VehicleManager.getSelectedVehicle().status.getDisplayName());

        List<SelectionOptions.SelectionOption> optionsList = buildVehicleStatusSelection();

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);

        VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle());
    }

    static List<SelectionOptions.SelectionOption> buildVehicleStatusSelection() {
        List<SelectionOptions.SelectionOption> optionList = new ArrayList<>();
        for (EStatus.Status status : EStatus.Status.values()) {
            if (status.getSelector() != null) {
                optionList.add(new SelectionOptions.SelectionOption(
                                status.getSelector(),
                                status.getDisplayName(),
                                _ -> {
                                    VehicleManager.getSelectedVehicle().setStatus(status);
                                    System.out.println("Vehicle status changed to " + status.getDisplayName());
                                }
                        )
                );
            }
        }
        return optionList;
    }

}
