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
    private final RegisterName condition;
    private final String target;

    public static final String OP_CODE = "jnz";

    /**
     * Constructor: a jump-not-zero instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label     optional label (can be null)
     * @param condition condition register
     * @param target    target label
     */
    public JNZInstruction(String label, RegisterName condition, String target) {
        super(label, OP_CODE);
        this.condition = condition;
        this.target = target;
    }

    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(condition) == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
        return m.getLabels().getAddress(target);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JNZInstruction other) {
            return Objects.equals(this.label, other.label)
                   && Objects.equals(this.condition, other.condition)
                   && Objects.equals(this.target, other.target);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, condition, target);
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + condition + " " + target;
    }
}
