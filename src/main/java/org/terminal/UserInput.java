package org.terminal;

import java.util.List;
import java.util.Scanner;

public class UserInput {
    public static Scanner input = new Scanner(System.in);

    public static Integer userTypeInteger(Integer argMin, Integer argMax) {
        Integer result;
        System.out.println("Input value between " + argMin + " and " + argMax + ":");
        try {
            result = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input - not a number. Try Again");
            return userTypeInteger(argMin, argMax);
        }

        if (argMin <= result && result < argMax) {
            return result;
        } else {
            System.out.println("Invalid value. Must be between " + argMin + " and " + argMax);
            return userTypeInteger(argMin, argMax);
        }
    }

    public static void userChooseFromOptions(List<Options.SelectionOption> optionsList) {
        String userSelector = input.nextLine();
        Options.SelectionOption chosenOption = optionsList.stream().
                filter(option -> userSelector.equalsIgnoreCase(option.selector)).findFirst().orElse(null);
        if (chosenOption != null) {
            chosenOption.execute();
        } else {
            System.out.println("Invalid selection, try another!");
            userChooseFromOptions(optionsList);
        }
    }
}
