package org.terminal.views;

import org.AppState;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.SelectionOptions.*;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class MainView {
    public static void mainView(AppState appState) {
        System.out.println(
                "     _____                                                                      \n" +
                        "    / ____|                                                            (_)      \n" +
                        "   | |  __    __ _   _ __    __ _    __ _    ___   _ __     ___   ___   _   ___ \n" +
                        "   | | |_ |  / _` | | '__|  / _` |  / _` |  / _ \\ | '_ \\   / _ \\ / __| | | / __|\n" +
                        "   | |__| | | (_| | | |    | (_| | | (_| | |  __/ | | | | |  __/ \\__ \\ | | \\__ \\\n" +
                        "    \\_____|  \\__,_| |_|     \\__,_|  \\__, |  \\___| |_| |_|  \\___| |___/ |_| |___/\n" +
                        "                                     __/ |                                      \n" +
                        "                                    |___/                                       "
        );
        System.out.println("     ╔════════════════════════════════════════════════════════════════════╗\n" +
                "     ║ \uD83D\uDD27 ⚙️  WRENCHES TURN, ENGINES BURN — WELCOME TO GARAGENESIS  ⚙️ \uD83D\uDD27 ║\n" +
                "     ╚════════════════════════════════════════════════════════════════════╝");

        List<SelectionOption> mainViewOptions = getSelectionOptionList(appState);

        displayAvailableOptions(mainViewOptions);
        userChooseFromOptions(mainViewOptions);
    }

    private static List<SelectionOption> getSelectionOptionList(AppState appState) {
        List<SelectionOption> mainViewOptions = new ArrayList<>();

        SelectionOption activeVehiclesSelectionOption = new SelectionOption(
                "A",
                "Active Vehicles Menu",
                _ -> ActiveVehiclesListView.activeVehiclesListView(appState));

        SelectionOption archivedVehiclesSelectionOption = new SelectionOption(
                "H",
                "Archived Vehicles Menu",
                _ -> ArchivedVehiclesListView.archivedVehiclesListView(appState));

        SelectionOption saveAppStateAndExitSelectionOption = new SelectionOption(
                "X",
                "Save AppState and exit",
                _ -> org.AppState.save(appState));

        mainViewOptions.add(activeVehiclesSelectionOption);
        mainViewOptions.add(archivedVehiclesSelectionOption);
        mainViewOptions.add(saveAppStateAndExitSelectionOption);

        return mainViewOptions;
    }


}
