package org.vehicles;

public class EPaintType {
    public enum PaintType {
        BASIC("BASIC", "B"),
        METALLIC("METALLIC", "M"),
        MATTE("MATTE", "T");

        private final String displayName;
        private final String selector;

        PaintType(String displayName, String selector) {
            this.displayName = displayName;
            this.selector = selector;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public String getSelector() {
            return this.selector;
        }

    }
}
