package sml.instruction.factory;


import org.springframework.stereotype.Component;
import sml.Instruction;
import sml.RegisterName;
import sml.instruction.OutInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents an out instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component(OutInstruction.OP_CODE)
public class OutInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    /**
     * Constructor: an out instruction factory.
     *
     * @param registerValueOf a function mapping a string to a register name
     */
    public OutInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new OutInstruction(label, registerValueOf.apply(paramSupplier.get()));
    }
}
