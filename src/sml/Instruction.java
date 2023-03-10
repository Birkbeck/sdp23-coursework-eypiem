package sml;


/**
 * Represents an abstract instruction.
 *
 * @author Amir Parsa Mahdian
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	/**
	 * Returns this instruction's label.
	 *
	 * @return this instruction's label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns this instruction's opcode.
	 *
	 * @return this instruction's opcode
	 */
	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	public abstract int execute(Machine machine);

	/**
	 * Returns this instruction's label formatted for console output.
	 *
	 * @return this instruction's label formatted for console output
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// What does abstract in the declaration below mean?
	// It makes sure that subclasses implement toString.
	@Override
	public abstract String toString();

	@Override
	public abstract boolean equals(Object o);

	@Override
	public abstract int hashCode();
}
