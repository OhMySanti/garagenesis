package org.terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static org.terminal.SelectionOptions.displayAvailableOptions;

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

    public static <T extends Enum<T>> T userChooseEnumOption(
            Class<T> enumClass,
            Function<T, String> getSelector,
            Function<T, String> getDisplayName
    ) {
        List<SelectionOptions.SelectionOption> optionsList = new ArrayList<>();
        AtomicReference<T> result = new AtomicReference<>(null);

        for (T constant : enumClass.getEnumConstants()) {
            optionsList.add(new SelectionOptions.SelectionOption(
                            getSelector.apply(constant),
                            getDisplayName.apply(constant),
                            _ -> result.set(constant)
                    )
            );
        }

        displayAvailableOptions(optionsList);
        SelectionOptions.userChooseFromOptions(optionsList);

        return result.get();
    }
}
