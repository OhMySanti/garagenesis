package org.terminal.views;

import org.AppState;
import org.terminal.SelectionOptions;

import java.util.List;

import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class ArchivedVehiclesListView {
    public static void archivedVehiclesListView(AppState appState) {
        System.out.println("ARCHIVED VEHICLES");

        List<SelectionOptions.SelectionOption> archivedVehiclesOptions =
                org.terminal.DisplayVehicles.buildVehicleSelection(appState, appState.getArchivedVehiclesList());

        SelectionOptions.SelectionOption mainMenuSelectionOption = new SelectionOptions.SelectionOption(
                "B",
                "Back to Main Menu",
                _ -> MainView.mainView(appState));

        archivedVehiclesOptions.add(mainMenuSelectionOption);

        displayAvailableOptions(archivedVehiclesOptions);
        userChooseFromOptions(archivedVehiclesOptions);
    }


}
