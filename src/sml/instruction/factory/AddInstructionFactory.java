package sml.instruction.factory;


import org.springframework.stereotype.Component;
import sml.Instruction;
import sml.RegisterName;
import sml.instruction.AddInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents an add instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component(AddInstruction.OP_CODE)
public class AddInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public AddInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new AddInstruction(label,
                registerValueOf.apply(paramSupplier.get()),
                registerValueOf.apply(paramSupplier.get()));
    }
}
