package cl.rematch;

/**
 * MatchGenerator is the output of findIter. It allows a user to iterate over
 * matches using a for loop.
 */
public class MatchGenerator implements Iterable<Match> {
    private final cl.rematch.internal.MatchGenerator generator;

    public MatchGenerator(cl.rematch.internal.MatchGenerator generator) {
        this.generator = generator;
    }

    @Override
    public MatchIterator iterator() {
        return new MatchIterator(generator.begin(), generator.end());
    }
}
