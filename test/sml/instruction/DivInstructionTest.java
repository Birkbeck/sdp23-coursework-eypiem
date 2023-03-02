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

class DivInstructionTest {
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
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -6);
        registers.set(EBX, 2);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-3, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(EAX, 0);
        registers.set(EBX, 0);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeEquals() {
        var i1 = new DivInstruction(null, EAX, EBX);
        var i2 = new DivInstruction(null, EAX, EBX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeEqualsTwo() {
        var i1 = new DivInstruction("L1", EAX, EBX);
        var i2 = new DivInstruction("L1", EAX, EBX);

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeNotEquals() {
        var i1 = new DivInstruction(null, EAX, EBX);
        var i2 = new DivInstruction("L1", EAX, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsTwo() {
        var i1 = new DivInstruction(null, EAX, EBX);
        var i2 = new DivInstruction(null, EBX, EBX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsThree() {
        var i1 = new DivInstruction(null, EAX, EBX);
        var i2 = new DivInstruction(null, EAX, EAX);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(new Object(), new DivInstruction(null, EAX, EBX));
    }

    @Test
    void executeNotEqualsFive() {
        Assertions.assertNotEquals(null, new DivInstruction(null, EAX, EBX));
    }

    @Test
    void executeValidToString() {
        var i = new DivInstruction(null, EAX, EBX);
        Assertions.assertEquals("div EAX EBX", i.toString());
    }

    @Test
    void executeValidToStringTwo() {
        var i = new DivInstruction("L1", EAX, EBX);
        Assertions.assertEquals("L1: div EAX EBX", i.toString());
    }
}
