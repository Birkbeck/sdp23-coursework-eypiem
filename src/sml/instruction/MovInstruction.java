package sml.instruction;


import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a move instruction.
 *
 * @author Amir Parsa Mahdian
 */
public class MovInstruction extends Instruction {
    public static final String OP_CODE = "mov";
    private final RegisterName result;
    private final int value;

    /**
     * Constructor: a move instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result result register
     * @param value  source integer
     */
    public MovInstruction(String label, RegisterName result, int value) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MovInstruction other) {
            return Objects.equals(this.label, other.label)
                   && Objects.equals(this.result, other.result)
                   && Objects.equals(this.value, other.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, value);
    }

    /**
     * representation of this instance,
     * in the form "label: opcode result value" if label exists, "opcode result value" otherwise.
     *
     * @return the string representation of this instruction
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }
}
