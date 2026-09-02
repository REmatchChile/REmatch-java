package cl.rematch;

/**
 * Constants used in the constructor of Query and MultiQuery.
 */
public class Constants {

    /**
     * The maximum number of deterministic states of the automata used in the
     * evaluation step.
     */
    public static final int MAX_DETERMINISTIC_STATES = 1000;

    /**
     * The maximum number of mempool duplications. This parameter limits the number
     * of nodes that can be allocated.
     */
    public static final int MAX_MEMPOOL_DUPLICATIONS = 8;

    /**
     * The size of the buffer when reading files. This parameter is ignored when the
     * document is of type String.
     */
    public static final int BUFFER_SIZE = 1024 * 1024;
}
