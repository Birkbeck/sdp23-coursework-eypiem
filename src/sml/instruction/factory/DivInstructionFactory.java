package sml.instruction.factory;


import org.springframework.stereotype.Component;
import sml.Instruction;
import sml.RegisterName;
import sml.instruction.DivInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents a division instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component(DivInstruction.OP_CODE)
public class DivInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public DivInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new DivInstruction(label,
                registerValueOf.apply(paramSupplier.get()),
                registerValueOf.apply(paramSupplier.get()));
    }
}
