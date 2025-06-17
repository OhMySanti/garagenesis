package org.vehicles;

public class EVehicleType {
    public enum VehicleType {
        AMPHIBIAN(1, "A", "Amphibian", 6),
        CAR(2, "C", "Car", 4),
        SHIP(3, "S", "Ship", 0);

        private final Integer sortingPriority;
        private final String displayName;
        private final String selector;
        private final Integer numberOfWheels;

        VehicleType(Integer priority, String selector, String name, int wheels) {
            this.sortingPriority = priority;
            this.displayName = name;
            this.selector = selector;
            this.numberOfWheels = wheels;
        }

        public Integer getSortingPriority() {
            return this.sortingPriority;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public String getSelector() {
            return this.selector;
        }

        public int getNumberOfWheels() {
            return this.numberOfWheels;
        }
    }
}