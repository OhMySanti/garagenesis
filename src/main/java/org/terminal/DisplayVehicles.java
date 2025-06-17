package org.terminal;

import org.AppState;
import org.services.Service;
import org.vehicles.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.VehicleManager.currency;
import static org.terminal.views.VehicleView.vehicleView;

public class DisplayVehicles {

    static ESortingMode.SortingMode activeSortingMode = ESortingMode.SortingMode.REGISTRATION;

    public static void setActiveSortingMode(ESortingMode.SortingMode sortingMode){
        activeSortingMode = sortingMode;
    }

    public static ESortingMode.SortingMode getActiveSortingMode(){
        return activeSortingMode;
    }

    public static List<SelectionOptions.SelectionOption> buildVehicleSelection(AppState appState, List<Vehicle> vehicleList) {
        List<SelectionOptions.SelectionOption> resultList = new ArrayList<>();
        vehicleList.sort(activeSortingMode.getComparator());
        int selector = 1;
        for (Vehicle vehicle : vehicleList) {
            resultList.add(new SelectionOptions.SelectionOption(Integer.toString(selector), vehicleToString(vehicle),
                    _ -> vehicleView(appState, vehicle)));
            selector++;
        }
        return resultList;
    }

//    public static String vehicleToString(Vehicle vehicle) {
//        return vehicle.registration + "\t" + vehicle.size + " " + vehicle.type + "\t" + vehicle.paintType.name() +
//                "\t" + vehicle.activeServices.size() + " ACTIVE SERVICES\t" + vehicle.status + "\t" +
//                vehicle.owner.getName() + "\t" + vehicle.owner.getPhoneNumber();
//    }

    public static String vehicleToString(Vehicle vehicle) {
        String vehicleDisplayLine = "%-10s  %-7s  %-10s  %-10s  %3s ACTIVE SERVICES   %-10s  %-15s  %-10s";
        vehicleDisplayLine += " id=" + vehicle.getId(); // TODO only for test purposes
        return String.format(vehicleDisplayLine, vehicle.registration, vehicle.size, vehicle.type.getDisplayName(),
                vehicle.paintType.name(), vehicle.activeServices.size(), vehicle.status, vehicle.owner.getName(),
                vehicle.owner.getPhoneNumber());
    }

    public static void displayArchivedServicesLists(Vehicle vehicle){
        if (!vehicle.historyOfServices.isEmpty()){
            System.out.println("ARCHIVED SERVICES:");
            int i = 1;
            for(Service service : vehicle.historyOfServices){
                System.out.println(i++ + ") " + service.getDisplayName());
            }
        }
    }

    public static String printCurrencyAmount(Integer n){
        String result = n.toString() + " " + currency;
        if (n != 1){
            result = result + "s";
        }
        return result;
    }

}
