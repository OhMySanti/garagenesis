package org.terminal.views;

import org.AppState;
import org.terminal.SelectionOptions;
import org.vehicles.*;

import java.util.ArrayList;
import java.util.List;

import static org.terminal.UserInput.*;
import static org.terminal.views.ActiveVehiclesListView.activeVehiclesListView;
import static org.terminal.SelectionOptions.displayAvailableOptions;

public class VehicleCreator {

    public static void createVehicle(AppState appState) {
        appState.getActiveVehiclesList().add(
                org.vehicles.VehicleFactory.createVehicle(
                        userChooseVehicleType(),
                        userTypeVehicleRegistration(),
                        userChooseVehicleSize(),
                        userChooseVehiclePaintType(),
                        userCreateVehicleOwner()
                )
        );

        activeVehiclesListView(appState);
    }

    static String userChooseVehicleType() {
        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();

        String[] result = {null};

        System.out.println("Choose vehicle type:");

        optionsList.add(new SelectionOptions.SelectionOption(
                "A",
                "Amphibian",
                _ -> result[0] = "Amphibian"
        ));

        optionsList.add(new SelectionOptions.SelectionOption(
                "C",
                "Car",
                _ -> result[0] = "Car"
        ));

        optionsList.add(new SelectionOptions.SelectionOption(
                "S",
                "Ship",
                _ -> result[0] = "Ship"
        ));

        displayAvailableOptions(optionsList);
        SelectionOptions.userChooseFromOptions(optionsList);

        return result[0];
    }


    static String userTypeVehicleRegistration() {
        System.out.println("Please input vehicle registration:");
        return input.nextLine();
    }

    static ESize.Size userChooseVehicleSize() {
        System.out.println("Choose vehicle size:");
        return userChooseEnumOption(ESize.Size.class, ESize.Size::getSelector, ESize.Size::getDisplayName);
    }

    static EPaintType.PaintType userChooseVehiclePaintType() {
        System.out.println("Choose paint type:");
        return userChooseEnumOption(EPaintType.PaintType.class, EPaintType.PaintType::getSelector,
                EPaintType.PaintType::getDisplayName);
    }

    static VehicleOwner userCreateVehicleOwner() {
        System.out.println("Please input vehicle owner's name:");
        String ownerName = input.nextLine();

        System.out.println("Please input vehicle owner's phone number:");
        String ownerPhoneNumber = input.nextLine();

        return new VehicleOwner(ownerName, ownerPhoneNumber);
    }
}
