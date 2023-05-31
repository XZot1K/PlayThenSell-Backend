package dev.zotware.apps.pts.enums;

public enum Condition {
    NEW("New"), VERY_GOOD("Very Good"), GOOD("Good"), ACCEPTABLE("Acceptable");

    private final String label;

    Condition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Condition get(String label) {
        for (int i = -1; ++i < values().length; ) {
            final Condition condition = values()[i];
            if (label.equalsIgnoreCase(condition.name()))
                return condition;
        }
        return null;
    }

}
