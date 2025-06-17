package org.terminal;

import org.AppState;

import java.util.List;

public class SelectionOptions {

    @FunctionalInterface
    public interface VarArgsFunction {
        void apply(Object... args);
    }

    public static class SelectionOption {
        AppState appState;
        String selector;
        String description;
        VarArgsFunction action;
        Object[] argsToUse;

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

    public static void displayAvailableOptions(List<SelectionOptions.SelectionOption> optionsList) {
        for (SelectionOptions.SelectionOption option : optionsList) {
            System.out.printf("[%s]\t%s\n", option.selector, option.description);
        }
    }

    public static void userChooseFromOptions(List<SelectionOption> optionsList) {
        String userSelector = UserInput.input.nextLine();
        SelectionOption chosenOption = optionsList.stream().
                filter(option ->
                        userSelector.equalsIgnoreCase(option.selector)).findFirst().orElse(null);
        if (chosenOption != null) {
            chosenOption.execute();
        } else {
            System.out.println("Invalid selection, try another!");
            userChooseFromOptions(optionsList);
        }
    }

}
