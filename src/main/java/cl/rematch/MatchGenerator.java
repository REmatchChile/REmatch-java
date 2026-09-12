package cl.rematch;

/**
 * MatchGenerator is the output of findIter. It allows a user to iterate over
 * matches using a for loop.
 */
public class MatchGenerator implements Iterable<Match> {
    private final cl.rematch.internal.MatchGenerator generator;

    /**
     * Creates a new MatchGenerator. This constructor is intended to be called
     * internally.
     * 
     * @param generator internal MatchGenerator.
     */
    public MatchGenerator(cl.rematch.internal.MatchGenerator generator) throws REmatchException {
        this.generator = generator;
    }

    @Override
    public MatchIterator iterator() throws REmatchException {
        return new MatchIterator(generator.begin(), generator.end());
    }
}
