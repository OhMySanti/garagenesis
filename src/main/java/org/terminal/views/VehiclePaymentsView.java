package org.terminal.views;

import org.AppState;
import org.VehicleManager;
import org.terminal.SelectionOptions;
import org.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.DisplayVehicles.printCurrencyAmount;
import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.views.VehiclePaymentRepaymentView.duePaymentRepaymentView;
import static org.terminal.SelectionOptions.displayAvailableOptions;
import static org.terminal.SelectionOptions.userChooseFromOptions;

public class VehiclePaymentsView {
    public static void vehiclePaymentsView(AppState appState, Vehicle vehicle) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(vehicle));
        System.out.println("Payment due : " + printCurrencyAmount(vehicle.duePayment));

        List<SelectionOptions.SelectionOption> vehiclePaymentViewOptions = buildVehiclePaymentsViewSelection(appState);

        displayAvailableOptions(vehiclePaymentViewOptions);
        userChooseFromOptions(vehiclePaymentViewOptions);
    }

    public static List<SelectionOptions.SelectionOption> buildVehiclePaymentsViewSelection(AppState appState) {
        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();

        SelectionOptions.SelectionOption duePaymentRepaymentOption = new SelectionOptions.SelectionOption(
                "R",
                "Reduce due debt",//how to name this option reasonably???
                _ -> duePaymentRepaymentView(appState, VehicleManager.getSelectedVehicle()));

        SelectionOptions.SelectionOption backToVehicleViewSelectionOption = new SelectionOptions.SelectionOption(
                "B",
                "Back to vehicle",
                _ -> VehicleView.vehicleView(appState, VehicleManager.getSelectedVehicle()));

        if (VehicleManager.getSelectedVehicle().duePayment > 0) {
            optionsList.add(duePaymentRepaymentOption);
        }

        optionsList.add(backToVehicleViewSelectionOption);

        return optionsList;
    }


}
