package sml.instruction.factory;


import org.springframework.stereotype.Component;
import sml.Instruction;
import sml.RegisterName;
import sml.instruction.MovInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents a move instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component(MovInstruction.OP_CODE)
public class MovInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public MovInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new MovInstruction(label,
                registerValueOf.apply(paramSupplier.get()),
                Integer.parseInt(paramSupplier.get()));
    }
}
