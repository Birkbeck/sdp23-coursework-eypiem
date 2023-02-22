package sml.instruction.factory;


import sml.Instruction;

import java.util.function.Supplier;

/**
 * Represents an instruction factory interface.
 *
 * @author Amir Parsa Mahdian
 */
public interface InstructionFactory {
    Instruction create(String label, Supplier<String> paramSupplier);
}
