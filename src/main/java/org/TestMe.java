package org;

import org.vehicles.*;


public class TestMe {
    public static void testMe(AppState appState) {
        appState.getActiveVehiclesList().add(
                new Amphibian(
                        "TESTEST1",
                        ESize.Size.MEDIUM,
                        EPaintType.PaintType.BASIC,
                        new VehicleOwner(
                                "Charlie1",
                                "1023931")));
        appState.getActiveVehiclesList().add(
                new Car(
                        "TESTEST2",
                        ESize.Size.LARGE,
                        EPaintType.PaintType.BASIC,
                        new VehicleOwner(
                                "Charlie2",
                                "1023931")));
        appState.getActiveVehiclesList().add(
                new Car(
                        "TESTEST3",
                        ESize.Size.LARGE,
                        EPaintType.PaintType.BASIC,
                        new VehicleOwner(
                                "Charlie3",
                                "1023931")));
    }
}
