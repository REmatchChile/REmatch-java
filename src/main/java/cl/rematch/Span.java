package cl.rematch;

/**
 * Span is a pair of integers that represent the start and end positions of a
 * match in a document.
 * 
 * @param first
 * @param second
 */
record Span(long first, long second) {
    /**
     * Creates a new Span. This constructor is intended to be called internally.
     * 
     * @param span
     */
    public Span(cl.rematch.internal.Span span) {
        this(span.first(), span.second());
    }
}
