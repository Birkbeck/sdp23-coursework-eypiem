package sml.instruction;


import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a jump-not-zero instruction.
 *
 * @author Amir Parsa Mahdian
 */
public class JNZInstruction extends Instruction {
    public static final String OP_CODE = "jnz";
    private final RegisterName source;
    private final String targetLabel;

    /**
     * Constructor: a jump-not-zero instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label       optional label (can be null)
     * @param source      condition register
     * @param targetLabel target label
     */
    public JNZInstruction(String label, RegisterName source, String targetLabel) {
        super(label, OP_CODE);
        this.source = source;
        this.targetLabel = targetLabel;
    }

    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(source) == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        return m.getLabels().getAddress(targetLabel);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JNZInstruction other) {
            return Objects.equals(this.label, other.label)
                   && Objects.equals(this.source, other.source)
                   && Objects.equals(this.targetLabel, other.targetLabel);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, source, targetLabel);
    }

    /**
     * representation of this instance,
     * in the form "label: opcode source targetLabel" if label exists, "opcode source targetLabel" otherwise.
     *
     * @return the string representation of this instruction
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + targetLabel;
    }
}
