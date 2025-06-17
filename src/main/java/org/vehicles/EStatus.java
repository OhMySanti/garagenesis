package org.vehicles;

public class EStatus {
    public enum Status {
        ARCHIVED(10, null, "ARCHIVED"),
        PENDING(1, "1", "PENDING"),
        ACCEPTED(2, "2", "ACCEPTED"),
        IN_SERVICE(3, "3", "IN SERVICE"),
        READY_TO_PICKUP(4, "4", "READY TO PICKUP");

        private final Integer sortingPriority;
        private final String displayName;
        private final String selector;

        Status(Integer priority, String selector, String name) {
            this.sortingPriority = priority;
            this.displayName = name;
            this.selector = selector;
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
    }
}
