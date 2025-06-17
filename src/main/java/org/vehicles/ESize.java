package org.vehicles;

public class ESize {
    public enum Size {
        //        UNSPECIFIED(10, "UNSPECIFIED"),
        SMALL(1, "SMALL", "S"),
        MEDIUM(2, "MEDIUM", "M"),
        LARGE(3, "LARGE", "L");

        private final Integer sortingPriority;
        private final String displayName;
        private final String selector;


        Size(Integer priority, String name, String selector) {
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
