package sml.instruction;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class MovInstructionTest {
    private Machine machine;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }

    @Test
    void executeValid() {
        Instruction instruction = new MovInstruction(null, EAX, 0);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        Instruction instruction = new MovInstruction(null, EAX, -1);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeEquals() {
        var i1 = new MovInstruction(null, EAX, -1);
        var i2 = new MovInstruction(null, EAX, -1);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeEqualsTwo() {
        var i1 = new MovInstruction("L1", EAX, 0);
        var i2 = new MovInstruction("L1", EAX, 0);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeNotEquals() {
        var i1 = new MovInstruction(null, EAX, 0);
        var i2 = new MovInstruction("L1", EAX, 0);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsTwo() {
        var i1 = new MovInstruction(null, EAX, 0);
        var i2 = new MovInstruction(null, EBX, 0);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsThree() {
        var i1 = new MovInstruction(null, EAX, -1);
        var i2 = new MovInstruction(null, EAX, 1);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(new Object(), new MovInstruction(null, EAX, 0));
    }

    @Test
    void executeNotEqualsFive() {
        Assertions.assertNotEquals(null, new MovInstruction(null, EAX, 0));
    }

    @Test
    void executeValidToString() {
        var i = new MovInstruction(null, EAX, -1);
        Assertions.assertEquals("mov EAX -1", i.toString());
    }

    @Test
    void executeValidToStringTwo() {
        var i = new MovInstruction("L1", EAX, 1);
        Assertions.assertEquals("L1: mov EAX 1", i.toString());
    }
}
