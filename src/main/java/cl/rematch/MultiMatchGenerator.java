package cl.rematch;

/**
 * MultiMatchGenerator is the output of findIter for multi spans. It allows a
 * user to iterate over multi matches using a for loop.
 */
public class MultiMatchGenerator implements Iterable<MultiMatch> {
    private final cl.rematch.internal.MultiMatchGenerator generator;

    /**
     * Creates a new MultiMatchGenerator. This constructor is intended to be called
     * internally.
     * 
     * @param generator the internal generator.
     */
    public MultiMatchGenerator(cl.rematch.internal.MultiMatchGenerator generator) throws REmatchException {
        this.generator = generator;
    }

    @Override
    public MultiMatchIterator iterator() throws REmatchException {
        return new MultiMatchIterator(generator.begin(), generator.end());
    }
}
