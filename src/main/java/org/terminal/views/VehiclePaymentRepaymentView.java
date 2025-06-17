package org.terminal.views;

import org.AppState;
import org.vehicles.Vehicle;

import static org.terminal.DisplayVehicles.printCurrencyAmount;
import static org.terminal.DisplayVehicles.vehicleToString;
import static org.terminal.UserInput.userTypeInteger;

public class VehiclePaymentRepaymentView {
    public static void duePaymentRepaymentView(AppState appState, Vehicle vehicle) {
        System.out.println("SELECTED VEHICLE:");
        System.out.println(vehicleToString(vehicle));
        System.out.println("Payment due : " + printCurrencyAmount(vehicle.duePayment));
        vehicle.duePayment -= userTypeInteger(0, vehicle.duePayment);
        VehiclePaymentsView.vehiclePaymentsView(appState, vehicle);
    }
}
