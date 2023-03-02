package sml;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * Represents a translator.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Amir Parsa Mahdian
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    private final static String INSTRUCTIONS = "instructions";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();

        String className = getProperties().getProperty(opcode);

        try {
            Class<? extends Instruction> instructionClass = Class.forName(className).asSubclass(Instruction.class);
            // Assumed each Instruction has one constructor
            Constructor<? extends Instruction> candidateConstructor =
                    ((Constructor<? extends Instruction>[]) instructionClass.getConstructors())[0];
            Class<?>[] paramCons = candidateConstructor.getParameterTypes();
            int argumentLen = candidateConstructor.getParameterCount();
            Object[] paramObjs = new Object[argumentLen];
            paramObjs[0] = label;

            for (int i = 1; i < argumentLen; i++) {
                paramObjs[i] = getParamObj(scan(), paramCons[i]);
            }
            return candidateConstructor.newInstance(paramObjs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO: add code for all other types of instructions

        // TODO: Then, replace the switch by using the Reflection API

        // TODO: Next, use dependency injection to allow this machine class
    }

    /**
     * Returns a Properties object containing instructions.
     *
     * @return a Properties object containing instructions
     */
    private static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream file = new FileInputStream(INSTRUCTIONS)) {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * Parses a string into a parameter object (integer, label or register).
     *
     * @param s   string to be parsed
     * @param c   parameter object type class
     * @return parameter object
     */
    private static Object getParamObj(String s, Class<?> c) {
        if (RegisterName.class.isAssignableFrom(c)) {
            return Register.valueOf(s);
        } else if (c == int.class) {
            return Integer.parseInt(s);
        } else if (c == String.class) {
            return s;
        }
        throw new RuntimeException("Unsupported parameter type '" + c.getSimpleName() + "'.");
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}
