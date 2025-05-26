package org.terminal;

import org.VehicleManager;
import org.vehicles.*;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.Menus.activeVehiclesListView;
import static org.terminal.Options.displayAvailableOptions;
import static org.terminal.UserInput.input;
import static org.terminal.UserInput.userChooseFromOptions;

public class NewVehicle {

    static void userCreateNewVehicle() {
        List<Options.SelectionOption> optionsList = new ArrayList<>();

        System.out.println("Choose vehicle type:");

        optionsList.add(new Options.SelectionOption(
                "A",
                "Amphibian",
                (Object... ignoredArgs) -> userCreateNewAmphibian()
        ));

        optionsList.add(new Options.SelectionOption(
                "C",
                "Car",
                (Object... ignoredArgs) -> userCreateNewCar()
        ));

        optionsList.add(new Options.SelectionOption(
                "S",
                "Ship",
                (Object... ignoredArgs) -> userCreateNewShip()
        ));

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);

        activeVehiclesListView();

    }

    static void userCreateNewAmphibian() {
        Amphibian newAmphibian = new Amphibian(userTypeVehicleRegistration(), userChooseVehicleSize(),
                userChooseVehiclePaintType(), userChooseOwner());
        VehicleManager.activeVehiclesList.add(newAmphibian);
    }

    static void userCreateNewCar() {
        Car newCar = new Car(userTypeVehicleRegistration(), userChooseVehicleSize(),
                userChooseVehiclePaintType(), userChooseOwner());
        VehicleManager.activeVehiclesList.add(newCar);
    }

    static void userCreateNewShip() {
        Ship newShip = new Ship(userTypeVehicleRegistration(), userChooseVehicleSize(),
                userChooseVehiclePaintType(), userChooseOwner());
        VehicleManager.activeVehiclesList.add(newShip);
    }


    static String userTypeVehicleRegistration() {
        System.out.println("Please input vehicle registration:");

        return input.nextLine();
    }

    static Vehicle.Size userChooseVehicleSize() {
        List<Options.SelectionOption> optionsList = new ArrayList<>();
        Vehicle.Size[] result = {null};

        System.out.println("Choose vehicle size:");

        for (Vehicle.Size size : Vehicle.Size.values()) {
            optionsList.add(new Options.SelectionOption(
                            size.getSelector(),
                            size.getDisplayName(),
                            (Object... ignoredArgs) -> result[0] = size
                    )
            );
        }

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);

        return result[0];
    }

    static Vehicle.PaintType userChooseVehiclePaintType() {
        List<Options.SelectionOption> optionsList = new ArrayList<>();
        Vehicle.PaintType[] result = {null};

        System.out.println("Choose paint type:");

        for (Vehicle.PaintType paintType : Vehicle.PaintType.values()) {
            optionsList.add(new Options.SelectionOption(
                            paintType.getSelector(),
                            paintType.getDisplayName(),
                            (Object... ignoredArgs) -> result[0] = paintType
                    )
            );
        }

        displayAvailableOptions(optionsList);
        userChooseFromOptions(optionsList);

        return result[0];
    }

    static VehicleOwner userChooseOwner() {
        System.out.println("Please input vehicle owner's name:");
        String ownerName = input.nextLine();

        System.out.println("Please input vehicle owner's phone number:");
        String ownerPhoneNumber = input.nextLine();

        return new VehicleOwner(ownerName, ownerPhoneNumber);
    }


}
