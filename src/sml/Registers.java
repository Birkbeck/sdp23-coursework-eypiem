package sml;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Represents registers.
 *
 * @author Amir Parsa Mahdian
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public enum Register implements RegisterName {
        EAX,
        EBX,
        ECX,
        EDX,
        ESP,
        EBP,
        ESI,
        EDI;
    }

    /**
     * Constructor: registers with default values 0.
     */
    public Registers() {
        clear(); // the class is final
    }

    /**
     * Resets this registers values to 0.
     */
    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value    new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register) register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register) register);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
            return registers.equals(other.registers);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    /**
     * representation of this instance,
     * in the form "[register = value, register = value, ..., register = value]"
     *
     * @return the string representation of the labels map
     */
    @Override
    public String toString() {
        return registers.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
