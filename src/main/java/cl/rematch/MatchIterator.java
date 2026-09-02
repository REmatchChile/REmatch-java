package cl.rematch;

import java.util.Iterator;

/**
 * MatchIterator is a forward iterator of matches.
 */
public class MatchIterator implements Iterator<Match> {

    private final cl.rematch.internal.MatchIterator current;
    private final cl.rematch.internal.MatchIterator end;

    private boolean needToIncrement = false;

    public MatchIterator(cl.rematch.internal.MatchIterator begin, cl.rematch.internal.MatchIterator end) {
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
    public Match next() {
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

    public Match operatorStar() {
        return new Match(current.operator_star());
    }

    public boolean operatorEquals(MatchIterator other) {
        return current.operatorEquals(other.current);
    }

    public boolean operatorNotEquals(MatchIterator other) {
        return current.operatorNotEquals(other.current);
    }
}
