package cl.rematch;

import java.util.Iterator;

/**
 * MatchIterator is a forward iterator of multi matches.
 */
public class MultiMatchIterator implements Iterator<MultiMatch> {

    private final cl.rematch.internal.MultiMatchIterator current;
    private final cl.rematch.internal.MultiMatchIterator end;

    private boolean needToIncrement = false;

    public MultiMatchIterator(cl.rematch.internal.MultiMatchIterator begin,
            cl.rematch.internal.MultiMatchIterator end) {
        this.current = begin;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }
        return !current.operatorEquals(end);
    }

    @Override
    public MultiMatch next() {
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

    public MultiMatch operatorStar() {
        current.operator_star();
        return new MultiMatch(current.operator_star());
    }

    public boolean operatorEquals(MultiMatchIterator other) {
        return current.operatorEquals(other.current);
    }

    public boolean operatorNotEquals(MultiMatchIterator other) {
        return current.operatorNotEquals(other.current);
    }
}
