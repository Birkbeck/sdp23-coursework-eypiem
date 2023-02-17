package sml.instruction;


import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a division instruction.
 *
 * @author Amir Parsa Mahdian
 */
public class DivInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "div";

    /**
     * Constructor: a division instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result result register
     * @param source source register
     */
    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        if (value2 == 0) {
            // TODO: Define an outcome for division by zero.
            m.getRegisters().set(result, 0);
        } else {
            m.getRegisters().set(result, value1 / value2);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DivInstruction other) {
            return Objects.equals(this.label, other.label)
                   && Objects.equals(this.result, other.result)
                   && Objects.equals(this.source, other.source);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, source);
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }
}
