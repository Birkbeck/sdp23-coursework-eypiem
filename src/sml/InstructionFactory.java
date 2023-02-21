package sml;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Represents an instruction factory.
 *
 * @author Amir Parsa Mahdian
 */
@Component
public class InstructionFactory {
    private static final ApplicationContext context;

    static {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    public static Instruction create(String label, String opcode, Supplier<String> scan) {
        return (Instruction) context.getBean(opcode, label, scan);
    }
}
