package sml;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import sml.instruction.*;

import java.util.function.Supplier;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public RegisterName registerName(String s) {
        return Registers.Register.valueOf(s);
    }

    @Bean(AddInstruction.OP_CODE)
    @Scope("prototype")
    public AddInstruction addInstruction(String label, Supplier<String> scan) {
        return new AddInstruction(label, registerName(scan.get()), registerName(scan.get()));
    }

    @Bean(SubInstruction.OP_CODE)
    @Scope("prototype")
    public SubInstruction subInstruction(String label, Supplier<String> scan) {
        return new SubInstruction(label, registerName(scan.get()), registerName(scan.get()));
    }

    @Bean(MulInstruction.OP_CODE)
    @Scope("prototype")
    public MulInstruction mulInstruction(String label, Supplier<String> scan) {
        return new MulInstruction(label, registerName(scan.get()), registerName(scan.get()));
    }

    @Bean(DivInstruction.OP_CODE)
    @Scope("prototype")
    public DivInstruction divInstruction(String label, Supplier<String> scan) {
        return new DivInstruction(label, registerName(scan.get()), registerName(scan.get()));
    }

    @Bean(MovInstruction.OP_CODE)
    @Scope("prototype")
    public MovInstruction movInstruction(String label, Supplier<String> scan) {
        return new MovInstruction(label, registerName(scan.get()), Integer.parseInt(scan.get()));
    }

    @Bean(OutInstruction.OP_CODE)
    @Scope("prototype")
    public OutInstruction outInstruction(String label, Supplier<String> scan) {
        return new OutInstruction(label, registerName(scan.get()));
    }

    @Bean(JNZInstruction.OP_CODE)
    @Scope("prototype")
    public JNZInstruction jnzInstruction(String label, Supplier<String> scan) {
        return new JNZInstruction(label, registerName(scan.get()), scan.get());
    }
}
