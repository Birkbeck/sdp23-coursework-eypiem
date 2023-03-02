package sml.instruction;


import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents an out instruction.
 *
 * @author Amir Parsa Mahdian
 */
public class OutInstruction extends Instruction {
    public static final String OP_CODE = "out";
    private final RegisterName source;

    /**
     * Constructor: an out instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param source source register
     */
    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        System.out.println(value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OutInstruction other) {
            return Objects.equals(this.label, other.label) && Objects.equals(this.source, other.source);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, source);
    }

    /**
     * representation of this instance,
     * in the form "label: opcode source" if label exists, "opcode source" otherwise.
     *
     * @return the string representation of this instruction
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }
}
