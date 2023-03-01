package sml.instruction.factory;


import sml.Instruction;

import java.util.function.Supplier;

/**
 * Represents an instruction factory interface.
 *
 * @author Amir Parsa Mahdian
 */
public interface InstructionFactory {
    /**
     * Returns a new instruction instance.
     *
     * @param label label of instruction
     * @param paramSupplier a string supplier populating the instruction parameter list
     * @return a new instruction instance
     */
    Instruction create(String label, Supplier<String> paramSupplier);
}
