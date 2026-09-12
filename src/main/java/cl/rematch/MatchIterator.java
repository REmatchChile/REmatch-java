package cl.rematch;

import java.util.Iterator;

/**
 * MatchIterator is a forward iterator of matches.
 */
public class MatchIterator implements Iterator<Match> {

    private final cl.rematch.internal.MatchIterator current;
    private final cl.rematch.internal.MatchIterator end;

    private boolean needToIncrement = false;

    /**
     * Creates a new MatchIterator. This constructor is intended to called
     * internally.
     * 
     * @param begin internal begin iterator.
     * @param end   internal end iterator.
     */
    public MatchIterator(cl.rematch.internal.MatchIterator begin, cl.rematch.internal.MatchIterator end) {
        this.current = begin;
        this.end = end;
    }

    @Override
    public boolean hasNext() throws REmatchException {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }
        return !current.operatorEquals(end);
    }

    @Override
    public Match next() throws REmatchException {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }

        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }

        needToIncrement = true;
        cl.rematch.internal.Match cppMatch = current.operator_star();
        return new Match(cppMatch);
    }

    /**
     * Obtains the internal match. This method is intended to be called internally.
     * 
     * @return the internal match.
     */
    public Match operatorStar() {
        return new Match(current.operator_star());
    }

    /**
     * Compares this to other MatchIterator.
     * 
     * @param other other iterator.
     * @return true if they are equal, false otherwise.
     */
    public boolean operatorEquals(MatchIterator other) {
        return current.operatorEquals(other.current);
    }

    /**
     * Compares this to other MatchIterator.
     * 
     * @param other other iterator.
     * @return true if they are not equal, false otherwise.
     */
    public boolean operatorNotEquals(MatchIterator other) {
        return current.operatorNotEquals(other.current);
    }
}
