package cl.rematch;


/**
 * Flags is used to pass options to the REQL Query.
 */
public class Flags {
    private final cl.rematch.internal.Flags cppFlags;

    public enum Mode {
        NONE,
        LINE_BY_LINE
    }

    public Flags(Mode mode) {
        switch (mode) {
            case LINE_BY_LINE:
                this.cppFlags = cl.rematch.internal.Flags.LINE_BY_LINE();
                break;
            case NONE:
            default:
                this.cppFlags = cl.rematch.internal.Flags.NONE();
                break;
        }
    }


    /**
     * Returns the internal value of the flags.
     * 
     * @return an int that represents the value of the flags.
     */
    public int getValue() {
        return this.cppFlags.getVal();
    }

    /**
     * Creates a Flags object with the value NONE.
     * 
     * @return a Flags object.
     */
    public static Flags none() {
        return new Flags(Mode.NONE);
    }

    /**
     * Creates a Flags object with the value LINE_BY_LINE
     * 
     * @return a Flags object.
     */
    public static Flags lineByLine() {
        return new Flags(Mode.LINE_BY_LINE);
    }
}
