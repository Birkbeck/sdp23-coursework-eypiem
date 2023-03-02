package sml.instruction.factory;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.RegisterName;
import sml.Registers;
import sml.instruction.MulInstruction;

import java.util.function.Function;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class MulInstructionFactoryTest {
    private static final Function<String, RegisterName> registerValueOf = Registers.Register::valueOf;
    private static final InstructionFactory factory = new MulInstructionFactory(registerValueOf);

    private int paramIndex;

    private String paramSupplier(String... params) {
        return paramIndex < params.length ? params[paramIndex++] : "";
    }

    @BeforeEach
    void setUp() {
        paramIndex = 0;
    }

    @Test
    void executeValid() {
        String label = null;
        String[] params = {"EAX", "EBX"};

        Instruction actual = factory.create(label, () -> paramSupplier(params));
        MulInstruction expected = new MulInstruction(label, EAX, EBX);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void executeValidTwo() {
        String label = "L1";
        String[] params = {"EAX", "EBX"};

        Instruction actual = factory.create(label, () -> paramSupplier(params));
        MulInstruction expected = new MulInstruction(label, EAX, EBX);

        Assertions.assertEquals(expected, actual);
    }
}
