package org;

import static org.terminal.views.MainView.mainView;

public class Main {
    public static void main(String[] args) {
        AppState appState = AppState.load();
        org.TestMe.testMe(appState);
//        AppState.save(appState);
        mainView(appState);
    }
}