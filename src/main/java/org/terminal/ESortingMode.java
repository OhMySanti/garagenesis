package org.terminal;

import org.vehicles.Vehicle;

import java.util.Comparator;

public class ESortingMode {
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

    static Comparator<Vehicle> vehicleComparatorByStatus =
            Comparator.comparing(v -> v.status.getSortingPriority());

    static Comparator<Vehicle> vehicleComparatorByType =
            (v1, v2) -> v1.type.getDisplayName().compareToIgnoreCase(v2.type.getDisplayName());

    static Comparator<Vehicle> vehicleComparatorByRegistration =
            (v1, v2) -> v1.registration.compareToIgnoreCase(v2.registration);
}
