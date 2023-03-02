package sml.instruction;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import java.util.List;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class JNZInstructionTest {
    private Machine machine;
    private Registers registers;
    private List<Instruction> program;
    private Labels labels;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        program = machine.getProgram();
        labels = machine.getLabels();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        program = null;
        labels = null;
    }

    @Test
    void executeValid() {
        program.add(new MovInstruction("L1", EAX, 2));
        labels.addLabel("L1", 0);
        registers.set(EAX, 1);
        Instruction instruction = new JNZInstruction(null, EAX, "L1");
        Assertions.assertEquals(0, instruction.execute(machine));
    }

    @Test
    void executeValidTwo() {
        program.add(new MovInstruction("L1", EAX, 2));
        labels.addLabel("L1", 0);
        registers.set(EAX, 0);
        Instruction instruction = new JNZInstruction(null, EAX, "L1");
        Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
    }

    @Test
    void executeEquals() {
        var i1 = new JNZInstruction(null, EAX, "L1");
        var i2 = new JNZInstruction(null, EAX, "L1");

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeEqualsTwo() {
        var i1 = new JNZInstruction("L1", EAX, "L1");
        var i2 = new JNZInstruction("L1", EAX, "L1");

        Assertions.assertEquals(i1, i2);
    }

    @Test
    void executeNotEquals() {
        var i1 = new JNZInstruction(null, EAX, "L1");
        var i2 = new JNZInstruction("L1", EAX, "L1");

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsTwo() {
        var i1 = new JNZInstruction(null, EAX, "L1");
        var i2 = new JNZInstruction(null, EBX, "L1");

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsThree() {
        var i1 = new JNZInstruction(null, EAX, "L1");
        var i2 = new JNZInstruction(null, EAX, null);

        Assertions.assertNotEquals(i1, i2);
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(new Object(), new JNZInstruction(null, EAX, "L1"));
    }

    @Test
    void executeNotEqualsFive() {
        Assertions.assertNotEquals(null, new JNZInstruction(null, EAX, "L1"));
    }

    @Test
    void executeValidToString() {
        var i = new JNZInstruction(null, EAX, "L1");
        Assertions.assertEquals("jnz EAX L1", i.toString());
    }

    @Test
    void executeValidToStringTwo() {
        var i = new JNZInstruction("L1", EAX, "L2");
        Assertions.assertEquals("L1: jnz EAX L2", i.toString());
    }
}
