package cl.rematch;

/**
 * MultiMatchGenerator is the output of findIter for multi spans. It allows a
 * user to iterate over multi matches using a for loop.
 */
public class MultiMatchGenerator implements Iterable<MultiMatch> {
    private final cl.rematch.internal.MultiMatchGenerator generator;

    public MultiMatchGenerator(cl.rematch.internal.MultiMatchGenerator generator) {
        this.generator = generator;
    }

    @Override
    public MultiMatchIterator iterator() {
        return new MultiMatchIterator(generator.begin(), generator.end());
    }
}
