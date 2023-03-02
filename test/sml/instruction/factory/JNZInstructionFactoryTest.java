package sml.instruction.factory;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.RegisterName;
import sml.Registers;
import sml.instruction.JNZInstruction;

import java.util.function.Function;

import static sml.Registers.Register.EAX;

class JNZInstructionFactoryTest {
    private static final Function<String, RegisterName> registerValueOf = Registers.Register::valueOf;
    private static final InstructionFactory factory = new JNZInstructionFactory(registerValueOf);
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
        String[] params = {"EAX", "L1"};

        Instruction actual = factory.create(label, () -> paramSupplier(params));
        JNZInstruction expected = new JNZInstruction(label, EAX, "L1");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void executeValidTwo() {
        String label = "L1";
        String[] params = {"EAX", "L2"};

        Instruction actual = factory.create(label, () -> paramSupplier(params));
        JNZInstruction expected = new JNZInstruction(label, EAX, "L2");

        Assertions.assertEquals(expected, actual);
    }
}
