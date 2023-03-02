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

class SubInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
    }

    @Test
    void executeEquals() {
        var i1 = new SubInstruction(null, EAX, EBX);
        var i2 = new SubInstruction(null, EAX, EBX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeEqualsTwo() {
        var i1 = new SubInstruction("L1", EAX, EBX);
        var i2 = new SubInstruction("L1", EAX, EBX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeNotEquals() {
        var i1 = new SubInstruction(null, EAX, EBX);
        var i2 = new SubInstruction("L1", EAX, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsTwo() {
        var i1 = new SubInstruction(null, EAX, EBX);
        var i2 = new SubInstruction(null, EBX, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsThree() {
        var i1 = new SubInstruction(null, EAX, EBX);
        var i2 = new SubInstruction(null, EAX, EAX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(new Object(), new SubInstruction(null, EAX, EBX));
    }

    @Test
    void executeNotEqualsFive() {
        Assertions.assertNotEquals(null, new SubInstruction(null, EAX, EBX));
    }

    @Test
    void executeValidToString() {
        var i = new SubInstruction(null, EAX, EBX);
        Assertions.assertEquals("sub EAX EBX", i.toString());
    }

    @Test
    void executeValidToStringTwo() {
        var i = new SubInstruction("L1", EAX, EBX);
        Assertions.assertEquals("L1: sub EAX EBX", i.toString());
    }
}
