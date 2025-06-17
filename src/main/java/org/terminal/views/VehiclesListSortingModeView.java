package org.terminal.views;

import org.AppState;
import org.terminal.ESortingMode;
import org.terminal.SelectionOptions;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.getActiveSortingMode;
import static org.terminal.DisplayVehicles.setActiveSortingMode;
import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class VehiclesListSortingModeView {
    public static void userVehicleSortingModeView(AppState appState) {
        System.out.println("Choose sorting method:");

        List<SelectionOptions.SelectionOption> optionsList = buildVehicleSortingModeSelection();

        SelectionOptions.SelectionOption mainMenuSelectionOption = new SelectionOptions.SelectionOption(
                "B",
                "Back to Main Menu",
                _ -> MainView.mainView(appState));

        optionsList.add(mainMenuSelectionOption);

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);

        System.out.println("Currently sorting by " + getActiveSortingMode().getDisplayName() + ":");

        ActiveVehiclesListView.activeVehiclesListView(appState);
    }

    static List<SelectionOptions.SelectionOption> buildVehicleSortingModeSelection() {
        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();
        for (ESortingMode.SortingMode sortingMode : ESortingMode.SortingMode.values()) {
            optionsList.add(new SelectionOptions.SelectionOption(
                            sortingMode.getSelector(),
                            sortingMode.getDisplayName(),
                            _ -> setActiveSortingMode(sortingMode)
                    )
            );
        }
        return optionsList;
    }


}
