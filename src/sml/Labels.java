package sml;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/**
 * Represents labels.
 *
 * @author Amir Parsa Mahdian
 */
public final class Labels {
    private final Map<String, Integer> labels = new HashMap<>();

    /**
     * Adds a label with the associated address to the map.
     *
     * @param label   the label
     * @param address the address the label refers to
     */
    public void addLabel(String label, int address) {
        Objects.requireNonNull(label);
        // TODO: Add a check that there are no label duplicates.
        if (labels.containsKey(label)) {
            throw new RuntimeException("Duplicate label " + label + " found.");
        }
        labels.put(label, address);
    }

    /**
     * Returns the address associated with the label.
     *
     * @param label the label
     * @return the address the label refers to
     */
    public int getAddress(String label) {
        // TODO: Where can NullPointerException be thrown here?
        //       When no mapping exists for the key or the value is explicitly set to null because null cannot be
        //       assigned to an int.
        //       Add code to deal with non-existent labels.
        Integer index = labels.get(label);
        if (index == null) {
            throw new RuntimeException("Label " + label + " not found.");
        }
        return labels.get(label);
    }

    /**
     * representation of this instance,
     * in the form "[label -> address, label -> address, ..., label -> address]"
     *
     * @return the string representation of the labels map
     */
    @Override
    public String toString() {
        // TODO: Implement the method using the Stream API (see also class Registers).
        return labels.entrySet()
                .stream()
                .map(e -> e.getKey() + " -> " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    // TODO: Implement equals and hashCode (needed in class Machine).

    @Override
    public boolean equals(Object o) {
        if (o instanceof Labels other) {
            return Objects.equals(this.labels, other.labels);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labels);
    }

    /**
     * Removes the labels
     */
    public void reset() {
        labels.clear();
    }
}
