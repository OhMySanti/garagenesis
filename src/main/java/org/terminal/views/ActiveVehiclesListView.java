package org.terminal.views;

import org.AppState;
import org.terminal.SelectionOptions;

import java.util.List;

import static org.terminal.SelectionOptions.*;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class ActiveVehiclesListView {
    public static void activeVehiclesListView(AppState appState) {
        System.out.println("ACTIVE VEHICLES");

        List<SelectionOptions.SelectionOption> activeVehiclesOptions =
                org.terminal.DisplayVehicles.buildVehicleSelection(appState, appState.getActiveVehiclesList());

        SelectionOption userCreateNewVehicleSelectionOption = new SelectionOption(
                "N",
                "Create a new vehicle",
                _ -> VehicleCreator.createVehicle(appState));

        SelectionOption mainMenuSelectionOption = new SelectionOption(
                "B",
                "Back to Main Menu",
                _ -> MainView.mainView(appState));

        SelectionOption vehicleSortingModeViewSelectionOption = new SelectionOption(
                "S",
                "Sorting mode",
                _ -> VehiclesListSortingModeView.userVehicleSortingModeView(appState));

        activeVehiclesOptions.add(userCreateNewVehicleSelectionOption);
        activeVehiclesOptions.add(mainMenuSelectionOption);
        activeVehiclesOptions.add(vehicleSortingModeViewSelectionOption);

        displayAvailableOptions(activeVehiclesOptions);
        userChooseFromOptions(activeVehiclesOptions);
    }


}
