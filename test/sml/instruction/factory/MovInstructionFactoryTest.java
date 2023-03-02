package sml.instruction.factory;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.RegisterName;
import sml.Registers;
import sml.instruction.MovInstruction;

import java.util.function.Function;

import static sml.Registers.Register.EAX;

class MovInstructionFactoryTest {
    private static final Function<String, RegisterName> registerValueOf = Registers.Register::valueOf;
    private static final MovInstructionFactory factory = new MovInstructionFactory(registerValueOf);
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
        String[] params = {"EAX", "-1"};

        Instruction actual = factory.create(label, () -> paramSupplier(params));
        MovInstruction expected = new MovInstruction(label, EAX, -1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void executeValidTwo() {
        String label = "L1";
        String[] params = {"EAX", "0"};

        Instruction actual = factory.create(label, () -> paramSupplier(params));
        MovInstruction expected = new MovInstruction(label, EAX, 0);

        Assertions.assertEquals(expected, actual);
    }
}
