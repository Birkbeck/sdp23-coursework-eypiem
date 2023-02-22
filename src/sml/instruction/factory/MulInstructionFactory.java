package sml.instruction.factory;


import org.springframework.stereotype.Component;
import sml.Instruction;
import sml.RegisterName;
import sml.instruction.MulInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents a multiplication instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component(MulInstruction.OP_CODE)
public class MulInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public MulInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new MulInstruction(label,
                registerValueOf.apply(paramSupplier.get()),
                registerValueOf.apply(paramSupplier.get()));
    }
}
