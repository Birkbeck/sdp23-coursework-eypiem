package sml.instruction;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class OutInstructionTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(stdout);
    }

    @Test
    void executeValid() {
        registers.set(EAX, 0);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("0", outContent.toString().trim());
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -1);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("-1", outContent.toString().trim());
    }

    @Test
    void executeEquals() {
        var i1 = new OutInstruction(null, EAX);
        var i2 = new OutInstruction(null, EAX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeEqualsTwo() {
        var i1 = new OutInstruction("L1", EAX);
        var i2 = new OutInstruction("L1", EAX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeNotEquals() {
        var i1 = new OutInstruction(null, EAX);
        var i2 = new OutInstruction("L1", EAX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsTwo() {
        var i1 = new OutInstruction(null, EAX);
        var i2 = new OutInstruction(null, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsThree() {
        Assertions.assertNotEquals(new Object(), new OutInstruction(null, EAX));
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(null, new OutInstruction(null, EAX));
    }

    @Test
    void executeValidToString() {
        var i = new OutInstruction(null, EAX);
        Assertions.assertEquals("out EAX", i.toString());
    }

    @Test
    void executeValidToStringTwo() {
        var i = new OutInstruction("L1", EAX);
        Assertions.assertEquals("L1: out EAX", i.toString());
    }
}
