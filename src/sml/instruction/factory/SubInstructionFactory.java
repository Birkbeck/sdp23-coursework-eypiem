package sml.instruction.factory;


import sml.Instruction;
import sml.RegisterName;
import sml.instruction.SubInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents a subtraction instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
public class SubInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public SubInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new SubInstruction(label,
                registerValueOf.apply(paramSupplier.get()),
                registerValueOf.apply(paramSupplier.get()));
    }
}
