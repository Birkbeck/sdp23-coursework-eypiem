package sml;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sml.instruction.*;
import sml.instruction.factory.*;

class InstructionFactoryCreatorTest {

    @Test
    public void executeAddInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(AddInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof AddInstructionFactory);
    }

    @Test
    public void executeSubInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(SubInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof SubInstructionFactory);
    }

    @Test
    public void executeMulInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(MulInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof MulInstructionFactory);
    }

    @Test
    public void executeDivInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(DivInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof DivInstructionFactory);
    }

    @Test
    public void executeMovInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(MovInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof MovInstructionFactory);
    }

    @Test
    public void executeOutInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(OutInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof OutInstructionFactory);
    }

    @Test
    public void executeJNZInstructionFactory() {
        InstructionFactory factory =
                InstructionFactoryCreator.getInstance().getInstructionFactory(JNZInstruction.OP_CODE);
        Assertions.assertTrue(factory instanceof JNZInstructionFactory);
    }

    @Test
    public void executeInvalid() {
        Assertions.assertThrows(RuntimeException.class,
                () -> InstructionFactoryCreator.getInstance().getInstructionFactory("inv"));
    }
}
