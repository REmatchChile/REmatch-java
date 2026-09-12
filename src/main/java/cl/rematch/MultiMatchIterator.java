package cl.rematch;

import java.util.Iterator;

/**
 * MatchIterator is a forward iterator of multi matches.
 */
public class MultiMatchIterator implements Iterator<MultiMatch> {

    private final cl.rematch.internal.MultiMatchIterator current;
    private final cl.rematch.internal.MultiMatchIterator end;

    private boolean needToIncrement = false;

    /**
     * Creates a new MultiMatchIterator. This constructor is intended to be called
     * internally.
     * 
     * @param begin internal begin iterator.
     * @param end   internal end iterator.
     */
    public MultiMatchIterator(cl.rematch.internal.MultiMatchIterator begin,
            cl.rematch.internal.MultiMatchIterator end) {
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
    public MultiMatch next() throws REmatchException {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }

        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }

        needToIncrement = true;
        cl.rematch.internal.MultiMatch cppMatch = current.operator_star();
        return new MultiMatch(cppMatch);
    }

    /**
     * Obtains the internal multi match. This method is intended to be called
     * internally.
     * 
     * @return the internal multi match.
     */
    public MultiMatch operatorStar() {
        current.operator_star();
        return new MultiMatch(current.operator_star());
    }

    /**
     * Compares this to other MultiMatchIterator.
     * 
     * @param other other iterator.
     * @return true if they are equal, false otherwise.
     */
    public boolean operatorEquals(MultiMatchIterator other) {
        return current.operatorEquals(other.current);
    }

    /**
     * Compares this to other MultiMatchIterator.
     * 
     * @param other other iterator.
     * @return false if they are equal, true otherwise.
     */
    public boolean operatorNotEquals(MultiMatchIterator other) {
        return current.operatorNotEquals(other.current);
    }
}
