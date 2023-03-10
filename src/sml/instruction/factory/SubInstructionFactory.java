package sml.instruction.factory;


import org.springframework.stereotype.Component;
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
@Component(SubInstruction.OP_CODE)
public class SubInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    /**
     * Constructor: a subtraction instruction factory.
     *
     * @param registerValueOf a function mapping a string to a register name
     */
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
