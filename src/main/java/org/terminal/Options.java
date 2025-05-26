package org.terminal;

import org.services.*;
import org.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.VehicleManager.completeActiveService;
import static org.terminal.DisplayVehicles.*;
import static org.terminal.Menus.*;
import static org.terminal.NewVehicle.userCreateNewVehicle;


public class Options {

    @FunctionalInterface
    public interface VarArgsFunction {
        void apply(Object... args);
    }

    public static class SelectionOption {
        String selector;
        String description;
        VarArgsFunction action;
        Object[] argsToUse; // store arguments here

        public SelectionOption(String selector, String description, VarArgsFunction action, Object... argsToUse) {
            this.selector = selector;
            this.description = description;
            this.action = action;
            this.argsToUse = argsToUse;
        }

        public void execute() {
            action.apply(argsToUse);
        }
    }

    public static void displayAvailableOptions(List<Options.SelectionOption> optionsList) {
        for (Options.SelectionOption option : optionsList) {
            System.out.printf("[%s]\t%s\n", option.selector, option.description);
        }
    }

//    =======================MENUS==============================

    static SelectionOption mainMenuSelectionOption = new SelectionOption(
            "M",
            "Main Menu",
            (Object... ignoredArgs) -> mainView());

    static SelectionOption activeVehiclesSelectionOption = new SelectionOption(
            "A",
            "Active Vehicles Menu",
            (Object... ignoredArgs) -> activeVehiclesListView());

    static SelectionOption archivedVehiclesSelectionOption = new SelectionOption(
            "H",
            "Archived Vehicles Menu",
            (Object... ignoredArgs) -> archivedVehiclesListView());

    static SelectionOption returnToSelectedVehicleViewSelectionOption = new SelectionOption(
            "B",
            "Back to vehicle",
            (Object... ignoredArgs) -> selectedVehicleView(selectedVehicle));

    static SelectionOption vehicleSortingModeViewSelectionOption = new SelectionOption(
            "S",
            "Sorting mode",
            (Object... ignoredArgs) -> userVehicleSortingModeView());

    static SelectionOption userCreateNewVehicleSelectionOption = new SelectionOption(
            "N",
            "Create a new vehicle",
            (Object... ignoredArgs) -> userCreateNewVehicle());


//     =======================SORTING==============================

    public static List<SelectionOption> buildVehicleSortingMethodViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();
        for (DisplayVehicles.SortingMode sortingMode : DisplayVehicles.SortingMode.values()) {
            optionsList.add(new Options.SelectionOption(
                            sortingMode.getSelector(),
                            sortingMode.getDisplayName(),
                            (Object... ignoredArgs) -> setActiveSortingMode(sortingMode)
                    )
            );
        }

        optionsList.add(activeVehiclesSelectionOption);

        return optionsList;
    }


//     =======================VEHICLE==============================

    public static List<SelectionOption> buildVehicleViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        optionsList.add(changeVehicleStatusOption);
        optionsList.add(vehicleServicesOption);
        optionsList.add(moveActiveVehicleToArchiveOption);
        optionsList.add(paymentStatusViewOption);
        optionsList.add(removeVehicleOption);

        return optionsList;
    }

    static SelectionOption changeVehicleStatusOption = new SelectionOption(
            "U",
            "Update vehicle status",
            (Object... ignoredArgs) -> vehicleChangeStatusView());

    static SelectionOption vehicleServicesOption = new SelectionOption(
            "S",
            "Services",
            (Object... ignoredArgs) -> vehicleActiveServicesView());

    static SelectionOption moveActiveVehicleToArchiveOption = new SelectionOption(
            "M",
            "Move vehicle to archive",
            (Object... ignoredArgs) -> org.VehicleManager.moveVehicleToHistory(selectedVehicle));

    static SelectionOption paymentStatusViewOption = new SelectionOption(
            "P",
            "Payments",
            (Object... ignoredArgs) -> vehiclePaymentsView(selectedVehicle));

    static SelectionOption removeVehicleOption = new SelectionOption(
            "X",
            "Remove vehicle",
            (Object... ignoredArgs) -> org.VehicleManager.removeVehicleFromActive(selectedVehicle));


    public static List<SelectionOption> buildVehiclePaymentsViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        if (selectedVehicle.duePayment > 0) {
            optionsList.add(duePaymentRepaymentOption);
        }

        optionsList.add(returnToSelectedVehicleViewSelectionOption);

        return optionsList;
    }

    static SelectionOption duePaymentRepaymentOption = new SelectionOption(
            "R",
            "Reduce due debt",//how to name this option reasonably???
            (Object... ignoredArgs) -> duePaymentRepaymentView(selectedVehicle));


//     =======================SERVICES==============================

    public static List<SelectionOption> buildActiveServicesListViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        int selector = 1;
        for (Service service : selectedVehicle.activeServices) {
            optionsList.add(new SelectionOption(
                    Integer.toString(selector),
                    service.getDisplayName(),
                    (Object... ignoredArgs) -> {
                        selectedService = service;
                        selectedServiceView(selectedService);
                    }));
            selector++;
        }
        optionsList.add(addNewServiceSelectionOption);

        optionsList.add(returnToSelectedVehicleViewSelectionOption);

        return optionsList;
    }


    public static List<SelectionOption> addNewServiceViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        optionsList.add(addServiceCleaning);
        optionsList.add(addServiceDentRepair);
        optionsList.add(addServiceEngineDiagnostics);
        optionsList.add(addServicePaintRetouch);
        optionsList.add(addServiceTyreChange);

        optionsList.add(returnToSelectedVehicleViewSelectionOption);

        return optionsList;
    }

    static SelectionOption addServiceCleaning = new SelectionOption(
            "1",
            "Cleaning",
            (Object... ignoredArgs) -> {
                Service service = (new Cleaning(selectedVehicle));
                if (service.isLegalService()) {
                    selectedVehicle.activeServices.add(service);
                    selectedVehicleView(selectedVehicle);
                } else {
                    System.out.println("Service can't be applied to this vehicle");
                }
            });

    static SelectionOption addServiceDentRepair = new SelectionOption(
            "2",
            "Dent repair",
            (Object... ignoredArgs) -> {
                Service service = (new DentRepair(selectedVehicle));
                if (service.isLegalService()) {
                    selectedVehicle.activeServices.add(service);
                    selectedVehicleView(selectedVehicle);
                } else {
                    System.out.println("Service can't be applied to this vehicle");
                }
            });

    static SelectionOption addServiceEngineDiagnostics = new SelectionOption(
            "3",
            "Engine diagnostics",
            (Object... ignoredArgs) -> {
                Service service = (new EngineDiagnostics(selectedVehicle));
                if (service.isLegalService()) {
                    selectedVehicle.activeServices.add(service);
                    selectedVehicleView(selectedVehicle);
                } else {
                    System.out.println("Service can't be applied to this vehicle");
                }
            });

    static SelectionOption addServicePaintRetouch = new SelectionOption(
            "4",
            "Paint retouch",
            (Object... ignoredArgs) -> {
                Service service = (new PaintRetouch(selectedVehicle));
                if (service.isLegalService()) {
                    selectedVehicle.activeServices.add(service);
                    selectedVehicleView(selectedVehicle);
                } else {
                    System.out.println("Service can't be applied to this vehicle");
                }
            });

    static SelectionOption addServiceTyreChange = new SelectionOption(
            "5",
            "Tyre change",
            (Object... ignoredArgs) -> {
                Service service = (new TyreChange(selectedVehicle));
                if (service.isLegalService()) {
                    selectedVehicle.activeServices.add(service);
                    selectedVehicleView(selectedVehicle);
                } else {
                    System.out.println("Service can't be applied to this vehicle");
                }
            });

    public static List<SelectionOption> buildSelectedServiceViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        optionsList.add(completeServiceSelectionOption);
        optionsList.add(backToVehicleViewSelectionOption);

        return optionsList;
    }

    public static List<SelectionOption> buildArchivedServicesListViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        optionsList.add(backToVehicleViewSelectionOption);

        return optionsList;
    }

    static SelectionOption completeServiceSelectionOption = new SelectionOption(
            "C",
            "Mark as completed and move to vehicle's history",
            (Object... ignoredArgs) -> {
                completeActiveService(selectedService);
                selectedVehicleView(selectedVehicle);
            });

    static SelectionOption addNewServiceSelectionOption = new SelectionOption(
            "N",
            "Add new service",
            (Object... ignoredArgs) -> addNewServiceView());

    //     =======================VEHICLE-STATUS==============================

    public static List<SelectionOption> buildVehicleStatusViewSelection() {
        List<SelectionOption> optionsList = new ArrayList<>();

        optionsList.add(changeVehicleStatusPending);
        optionsList.add(changeVehicleStatusAccepted);
        optionsList.add(changeVehicleStatusInService);
        optionsList.add(changeVehicleStatusReadyToPickUp);
        optionsList.add(backToVehicleViewSelectionOption);

        return optionsList;
    }

    static SelectionOption changeVehicleStatusPending = new SelectionOption(
            "1",
            "PENDING",
            (Object... ignoredArgs) -> selectedVehicle.changeStatus(Vehicle.Status.PENDING));

    static SelectionOption changeVehicleStatusAccepted = new SelectionOption(
            "2",
            "ACCEPTED",
            (Object... ignoredArgs) -> selectedVehicle.changeStatus(Vehicle.Status.ACCEPTED));

    static SelectionOption changeVehicleStatusInService = new SelectionOption(
            "3",
            "IN SERVICE",
            (Object... ignoredArgs) -> selectedVehicle.changeStatus(Vehicle.Status.IN_SERVICE));

    static SelectionOption changeVehicleStatusReadyToPickUp = new SelectionOption(
            "4",
            "READY TO PICK UP",
            (Object... ignoredArgs) -> selectedVehicle.changeStatus(Vehicle.Status.READY_TO_PICKUP));

    static SelectionOption backToVehicleViewSelectionOption = new SelectionOption(
            "B",
            "Back to vehicle",
            (Object... ignoredArgs) -> selectedVehicleView(selectedVehicle));
}
