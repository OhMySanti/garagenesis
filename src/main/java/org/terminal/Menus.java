package org.terminal;

import org.services.Service;
import org.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.VehicleManager.activeVehiclesList;
import static org.VehicleManager.archivedVehiclesList;
import static org.VehicleManager.currency;
import static org.terminal.DisplayVehicles.*;
import static org.terminal.Options.*;
import static org.terminal.UserInput.userChooseFromOptions;
import static org.terminal.UserInput.userTypeInteger;

public class Menus {

    static Vehicle selectedVehicle;
    static Service selectedService;

    public static void mainView() {
        System.out.println("Welcome to Garagenesis!\n");
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

        List<Options.SelectionOption> mainViewOptions = new ArrayList<>();

        mainViewOptions.add(activeVehiclesSelectionOption);
        mainViewOptions.add(archivedVehiclesSelectionOption);

        displayAvailableOptions(mainViewOptions);
        userChooseFromOptions(mainViewOptions);
    }

    public static void activeVehiclesListView() {
        System.out.println("ACTIVE VEHICLES");

        List<SelectionOption> activeVehiclesOptions = buildVehicleSelection(activeVehiclesList);

        activeVehiclesOptions.add(userCreateNewVehicleSelectionOption);
        activeVehiclesOptions.add(mainMenuSelectionOption);
        activeVehiclesOptions.add(vehicleSortingModeViewSelectionOption);

        displayAvailableOptions(activeVehiclesOptions);
        userChooseFromOptions(activeVehiclesOptions);
    }

    public static void archivedVehiclesListView() {
        System.out.println("ARCHIVED VEHICLES");

        List<SelectionOption> archivedVehiclesOptions = buildVehicleSelection(archivedVehiclesList);
        archivedVehiclesOptions.add(mainMenuSelectionOption);

        displayAvailableOptions(archivedVehiclesOptions);
        userChooseFromOptions(archivedVehiclesOptions);
    }

    public static void userVehicleSortingModeView() {
        System.out.println("Choose sorting method:");

        List<Options.SelectionOption> optionsList = buildVehicleSortingMethodViewSelection();

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);

        System.out.println("Currently sorting by " + getActiveSortingMode().getDisplayName() + ":");

        activeVehiclesListView();
    }


    public static void selectedVehicleView(Vehicle vehicle) {
        selectedVehicle = vehicle;
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(vehicle));

        List<SelectionOption> vehicleViewOptions = buildVehicleViewSelection();
        vehicleViewOptions.add(mainMenuSelectionOption);

        displayAvailableOptions(vehicleViewOptions);
        userChooseFromOptions(vehicleViewOptions);
    }

    public static void vehicleChangeStatusView() {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(selectedVehicle));
        System.out.println("STATUS: " + selectedVehicle.status.getDisplayName());

        List<SelectionOption> vehicleStatusViewOptions = buildVehicleStatusViewSelection();

        displayAvailableOptions(vehicleStatusViewOptions);
        userChooseFromOptions(vehicleStatusViewOptions);

        selectedVehicleView(selectedVehicle);
    }

    public static void vehicleActiveServicesView() {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(selectedVehicle));
        displayArchivedServicesLists(selectedVehicle);
        System.out.println("ACTIVE SERVICES: " + selectedVehicle.activeServices.size());

        List<SelectionOption> vehicleServicesViewOptions = buildActiveServicesListViewSelection();

        displayAvailableOptions(vehicleServicesViewOptions);
        userChooseFromOptions(vehicleServicesViewOptions);
    }

    public static void addNewServiceView() {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(selectedVehicle));
        System.out.println("ACTIVE SERVICES: " + selectedVehicle.activeServices.size());

        List<SelectionOption> addNewServiceViewOptions = addNewServiceViewSelection();

        displayAvailableOptions(addNewServiceViewOptions);
        userChooseFromOptions(addNewServiceViewOptions);
    }

    public static void selectedServiceView(Service service) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(selectedVehicle));
        System.out.println("SELECTED Service:");
        System.out.println(service.getDisplayName());

        List<SelectionOption> selectedServiceViewOptions = buildSelectedServiceViewSelection();

        displayAvailableOptions(selectedServiceViewOptions);
        userChooseFromOptions(selectedServiceViewOptions);
    }

    public static void vehiclePaymentsView(Vehicle vehicle) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(vehicle));
        System.out.println("Payment due : " + printCurrencyAmount(vehicle.duePayment));

        List<SelectionOption> vehiclePaymentViewOptions = buildVehiclePaymentsViewSelection();

        displayAvailableOptions(vehiclePaymentViewOptions);
        userChooseFromOptions(vehiclePaymentViewOptions);
    }

    public static void duePaymentRepaymentView(Vehicle vehicle) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(vehicle));
        System.out.println("Payment due : " + printCurrencyAmount(vehicle.duePayment));
        vehicle.duePayment -= userTypeInteger(0, vehicle.duePayment);
        vehiclePaymentsView(vehicle);
    }
}
