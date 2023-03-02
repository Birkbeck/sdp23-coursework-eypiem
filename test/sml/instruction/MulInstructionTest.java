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

class MulInstructionTest {
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
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(EAX, 0);
        registers.set(EBX, 0);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeEquals() {
        var i1 = new MulInstruction(null, EAX, EBX);
        var i2 = new MulInstruction(null, EAX, EBX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeEqualsTwo() {
        var i1 = new MulInstruction("L1", EAX, EBX);
        var i2 = new MulInstruction("L1", EAX, EBX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeNotEquals() {
        var i1 = new MulInstruction(null, EAX, EBX);
        var i2 = new MulInstruction("L1", EAX, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsTwo() {
        var i1 = new MulInstruction(null, EAX, EBX);
        var i2 = new MulInstruction(null, EBX, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsThree() {
        var i1 = new MulInstruction(null, EAX, EBX);
        var i2 = new MulInstruction(null, EAX, EAX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(new Object(), new MulInstruction(null, EAX, EBX));
    }

    @Test
    void executeNotEqualsFive() {
        Assertions.assertNotEquals(null, new MulInstruction(null, EAX, EBX));
    }

    @Test
    void executeValidToString() {
        var i = new MulInstruction(null, EAX, EBX);
        Assertions.assertEquals("mul EAX EBX", i.toString());
    }

    @Test
    void executeValidToStringTwo() {
        var i = new MulInstruction("L1", EAX, EBX);
        Assertions.assertEquals("L1: mul EAX EBX", i.toString());
    }
}
