package sml;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.instruction.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class TranslatorTest {
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
    void executeValid() throws IOException {
        Translator t = new Translator("test/res/test1.sml");
        t.readAndTranslate(machine.getLabels(), machine.getProgram());

        Labels expectedLabels = new Labels();
        expectedLabels.addLabel("L1", 0);

        List<Instruction> expectedProgram = new ArrayList<>();
        expectedProgram.add(new MovInstruction("L1", EAX, 66));
        expectedProgram.add(new AddInstruction(null, EAX, EAX));
        expectedProgram.add(new DivInstruction(null, EAX, EAX));
        expectedProgram.add(new SubInstruction(null, EBX, EAX));
        expectedProgram.add(new MulInstruction(null, EAX, EBX));
        expectedProgram.add(new OutInstruction(null, EAX));
        expectedProgram.add(new JNZInstruction(null, EAX, "L1"));

        Assertions.assertAll(() -> Assertions.assertEquals(machine.getLabels(), expectedLabels),
                () -> Assertions.assertEquals(machine.getProgram(), expectedProgram));
    }
}
