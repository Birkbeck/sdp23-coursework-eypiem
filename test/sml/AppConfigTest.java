package sml;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sml.instruction.*;
import sml.instruction.factory.*;

import java.util.function.Function;

class AppConfigTest {
    private static ApplicationContext context;

    @BeforeAll
    static void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterAll
    static void tearDown() {
        context = null;
    }

    @Test
    public void functionExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(Function.class));
    }

    @Test
    public void addInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(AddInstruction.OP_CODE, InstructionFactory.class));
    }

    @Test
    public void subInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(SubInstruction.OP_CODE, InstructionFactory.class));
    }

    @Test
    public void mulInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(MulInstruction.OP_CODE, InstructionFactory.class));
    }

    @Test
    public void divInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(DivInstruction.OP_CODE, InstructionFactory.class));
    }

    @Test
    public void movInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(MovInstruction.OP_CODE, InstructionFactory.class));
    }

    @Test
    public void outInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(OutInstruction.OP_CODE, InstructionFactory.class));
    }

    @Test
    public void jnzInstructionFactoryExistsInApplicationContext() {
        Assertions.assertNotNull(context.getBean(JNZInstruction.OP_CODE, InstructionFactory.class));
    }
}
