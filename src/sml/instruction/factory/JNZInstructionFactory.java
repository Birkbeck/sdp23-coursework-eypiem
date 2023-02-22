package sml.instruction.factory;


import org.springframework.stereotype.Component;
import sml.Instruction;
import sml.RegisterName;
import sml.instruction.JNZInstruction;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Represents a jump-not-zero instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component(JNZInstruction.OP_CODE)
public class JNZInstructionFactory implements InstructionFactory {
    private final Function<String, RegisterName> registerValueOf;

    public JNZInstructionFactory(Function<String, RegisterName> registerValueOf) {
        this.registerValueOf = registerValueOf;
    }

    @Override
    public Instruction create(String label, Supplier<String> paramSupplier) {
        return new JNZInstruction(label, registerValueOf.apply(paramSupplier.get()), paramSupplier.get());
    }
}
