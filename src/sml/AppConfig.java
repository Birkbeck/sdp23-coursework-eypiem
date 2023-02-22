package sml;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sml.instruction.*;
import sml.instruction.factory.*;

import java.util.function.Function;

@Configuration
public class AppConfig {

    @Bean
    Function<String, RegisterName> registerValueOf() {
        return Registers.Register::valueOf;
    }

    @Bean(AddInstruction.OP_CODE)
    public InstructionFactory addInstructionFactory() {
        return new AddInstructionFactory(registerValueOf());
    }

    @Bean(SubInstruction.OP_CODE)
    public InstructionFactory subInstructionFactory() {
        return new SubInstructionFactory(registerValueOf());
    }

    @Bean(MulInstruction.OP_CODE)
    public InstructionFactory mulInstructionFactory() {
        return new MulInstructionFactory(registerValueOf());
    }

    @Bean(DivInstruction.OP_CODE)
    public InstructionFactory divInstructionFactory() {
        return new DivInstructionFactory(registerValueOf());
    }

    @Bean(MovInstruction.OP_CODE)
    public InstructionFactory movInstructionFactory() {
        return new MovInstructionFactory(registerValueOf());
    }

    @Bean(OutInstruction.OP_CODE)
    public InstructionFactory outInstructionFactory() {
        return new OutInstructionFactory(registerValueOf());
    }

    @Bean(JNZInstruction.OP_CODE)
    public InstructionFactory jnzInstructionFactory() {
        return new JNZInstructionFactory(registerValueOf());
    }
}
