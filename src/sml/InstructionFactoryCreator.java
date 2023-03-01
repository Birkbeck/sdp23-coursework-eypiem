package sml;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sml.instruction.factory.InstructionFactory;


/**
 * Represents an instruction factory creator.
 *
 * @author Amir Parsa Mahdian
 */
public class InstructionFactoryCreator {
    private static volatile InstructionFactoryCreator instance;

    /**
     * Spring application context
     */
    private final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    /**
     * Returns the instance of this singleton class.
     *
     * @return the instance of this singleton class
     */
    public static InstructionFactoryCreator getInstance() {
        InstructionFactoryCreator result = instance;
        if (result != null) {
            return result;
        }
        synchronized (InstructionFactoryCreator.class) {
            if (instance == null) {
                instance = new InstructionFactoryCreator();
            }
            return instance;
        }
    }

    /**
     * Returns an instruction factory corresponding to the opcode provided.
     *
     * @param opcode the instruction's opcode
     * @return an instruction factory corresponding to the opcode provided
     */
    public InstructionFactory getInstructionFactory(String opcode) {
        try {
            return context.getBean(opcode, InstructionFactory.class);
        } catch (BeansException e) {
            throw new RuntimeException("No instruction '" + opcode + "' available");
        }
    }
}
