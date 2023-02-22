package sml.instruction.factory;


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
public class OutInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public OutInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new OutInstruction(label, registerValueOf.apply(paramSupplier.get()));
    }
}
