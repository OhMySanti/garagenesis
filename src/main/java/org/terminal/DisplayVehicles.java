package org.terminal;

import org.services.Service;
import org.vehicles.Amphibian;
import org.vehicles.Car;
import org.vehicles.Vehicle;
import org.vehicles.VehicleOwner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.VehicleManager.activeVehiclesList;
import static org.VehicleManager.currency;
import static org.terminal.Menus.selectedVehicleView;
import static org.terminal.Options.vehicleSortingModeViewSelectionOption;

public class DisplayVehicles {

    Integer vehiclesDisplayedPerPage = 5;

    static SortingMode activeSortingMode = SortingMode.REGISTRATION;

    public static void setActiveSortingMode(SortingMode sortingMode){
        activeSortingMode = sortingMode;
    }

    public static SortingMode getActiveSortingMode(){
        return activeSortingMode;
    }

    public void setVehiclesDisplayedPerPage(int newNumberPerPage) {
        vehiclesDisplayedPerPage = newNumberPerPage;
    }

    public static Comparator<Vehicle> vehicleComparatorByStatus =
            Comparator.comparing(v -> v.status.getSortingPriority());

    public static Comparator<Vehicle> vehicleComparatorByType =
            (v1, v2) -> v1.type.compareToIgnoreCase(v2.type);

    public static Comparator<Vehicle> vehicleComparatorByRegistration =
            (v1, v2) -> v1.registration.compareToIgnoreCase(v2.registration);

    public enum SortingMode {
        REGISTRATION("REGISTRATION", "R", vehicleComparatorByRegistration),
        TYPE("TYPE", "T", vehicleComparatorByType),
        STATUS("STATUS", "S", vehicleComparatorByStatus);

        private final String displayName;
        private final String selector;
        private final Comparator<Vehicle> sortingComparator;

        SortingMode(String name, String selector, Comparator<Vehicle> comparator) {
            this.displayName = name;
            this.sortingComparator = comparator;
            this.selector = selector;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getSelector() {
            return selector;
        }

        public Comparator<Vehicle> getComparator() {
            return sortingComparator;
        }
    }

    public static List<Options.SelectionOption> buildVehicleSelection(List<Vehicle> vehicleList) {
        List<Options.SelectionOption> resultList = new ArrayList<>();
        int selector = 1;
        for (Vehicle vehicle : vehicleList) {
            resultList.add(new Options.SelectionOption(Integer.toString(selector), vehicleToString(vehicle),
                    (Object... ignoredArgs) -> selectedVehicleView(vehicle)));
            selector++;
        }

        resultList.add(vehicleSortingModeViewSelectionOption);

        return resultList;
    }

    public static String vehicleToString(Vehicle vehicle) {
        return vehicle.registration + "\t" + vehicle.size + " " + vehicle.type + "\t" + vehicle.paintType.name() +
                "\t" + vehicle.activeServices.size() + " ACTIVE SERVICES\t" + vehicle.status + "\t" +
                vehicle.owner.getName() + "\t" + vehicle.owner.getPhoneNumber();
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
        String result;
        result = n.toString() + " " + currency;
        if (n != 1){
            result = result + "s";
        }
        return result;
    }

    public static void testMe() {
        activeVehiclesList.add(new Car("JP2 2137", Vehicle.Size.LARGE, Vehicle.PaintType.MATTE, new VehicleOwner("PufPuf", "123123")));
        activeVehiclesList.add(new Car("XDXD-SEX", Vehicle.Size.MEDIUM, Vehicle.PaintType.METALLIC, new VehicleOwner("Bartek", "696969")));
        activeVehiclesList.add(new Amphibian("RUXAXSUX", Vehicle.Size.LARGE, Vehicle.PaintType.BASIC, new VehicleOwner("Charlie", "1023931")));
    }
}
