package cl.rematch;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiQuery represents a REQL query with support for multimatch.
 */
public class MultiQuery {

    private final cl.rematch.internal.MultiQuery cppMultiQuery;

    /**
     * Creates a new MultiQuery.
     * 
     * @param pattern                the REql query.
     * @param flags                  the flags.
     * @param maxMempoolDuplications the number of duplications.
     * @param maxDeterministicStates the maximum number of deterministic states.
     * @param bufferSize             the buffer size.
     */
    public MultiQuery(String pattern, Flags flags, int maxMempoolDuplications, int maxDeterministicStates,
            int bufferSize) throws REmatchException {
        this.cppMultiQuery = new cl.rematch.internal.MultiQuery(pattern, flags.getValue(), maxMempoolDuplications,
                maxDeterministicStates, bufferSize);
    }

    /**
     * Creates a new MultiQuery with the default flags and parameters.
     * 
     * @param pattern the REQL query.
     */
    public MultiQuery(String pattern) throws REmatchException {
        this(pattern, Flags.none(), Constants.MAX_MEMPOOL_DUPLICATIONS, Constants.MAX_MEMPOOL_DUPLICATIONS,
                Constants.BUFFER_SIZE);
    }

    /**
     * Creates a new MultiQuery with the default flags.
     * 
     * @param pattern the REQL query.
     * @param flags   the flags.
     */
    public MultiQuery(String pattern, Flags flags) throws REmatchException {
        this(pattern, flags, Constants.MAX_MEMPOOL_DUPLICATIONS, Constants.MAX_DETERMINISTIC_STATES,
                Constants.BUFFER_SIZE);
    }

    /**
     * Returns true if there is a match in the document and false otherwise.
     * 
     * @param document the document.
     * @return true if there is a match, false otherwise.
     */
    public boolean check(String document) throws REmatchException {
        return cppMultiQuery.check(document);
    }

    /**
     * Returns true if there is a match in the document and false otherwise. It
     * takes a reader as document.
     * 
     * @param reader the reader.
     * @return true if there is a match, false otherwise.
     */
    public boolean check(Reader reader) throws REmatchException {
        return cppMultiQuery.check(reader.cppReader.get());
    }

    /**
     * Returns a list containing the variables present in the query.
     * 
     * @return a list of variables.
     */
    public List<String> variables() {
        cl.rematch.internal.StringVector cppVariables = cppMultiQuery.variables();
        List<String> variables = new ArrayList<>();
        for (int i = 0; i < cppVariables.size(); i++) {
            variables.add(cppVariables.at(i));
        }
        return variables;
    }

    /**
     * Returns the first match in the document. It returns null if no match is
     * found.
     * 
     * @param document the document.
     * @return a match or null.
     */
    public MultiMatch findOne(String document) throws REmatchException {
        cl.rematch.internal.OptionalMultiMatch match = cppMultiQuery.findone(document);
        if (match.hasValue()) {
            return new MultiMatch(match.value());
        }
        return null;
    }

    /**
     * Returns the first match in the document. It returns null if no match is
     * found. It takes a reader as document.
     * 
     * @param reader the reader.
     * @return a match or null.
     */
    public MultiMatch findOne(Reader reader) throws REmatchException {
        cl.rematch.internal.OptionalMultiMatch match = cppMultiQuery.findone(reader.cppReader.get());
        if (match.hasValue()) {
            return new MultiMatch(match.value());
        }
        return null;
    }

    /**
     * Returns a list containing up to {@code limit} results from the document.
     * 
     * @param document the document.
     * @param limit    the maximum number of matches.
     * @return a list of matches.
     */
    public List<MultiMatch> findMany(String document, int limit) throws REmatchException {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findmany(document, limit);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    /**
     * Returns a list containing up to {@code limit} results from the document. It
     * takes a reader as document.
     * 
     * @param reader the reader.
     * @param limit  the maximum number of matches.
     * @return a list of matches.
     */
    public List<MultiMatch> findMany(Reader reader, int limit) throws REmatchException {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findmany(reader.cppReader.get(), limit);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    /**
     * Returns a list containing all matches found in the document.
     * 
     * @param document the document.
     * @return a list of matches.
     */
    public List<MultiMatch> findAll(String document) throws REmatchException {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findall(document);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    /**
     * Returns a list containing all matches found in the document. It takes a
     * reader as document.
     * 
     * @param reader the reader.
     * @return a list of matches.
     */
    public List<MultiMatch> findAll(Reader reader) throws REmatchException {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findall(reader.cppReader.get());
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    /**
     * Returns a generator that produces all the matches in the document.
     * 
     * @param document the document.
     * @return a generator of matches.
     */
    public MultiMatchGenerator findIter(String document) throws REmatchException {
        cl.rematch.internal.MultiMatchGenerator generator = cppMultiQuery.finditer(document);
        return generator != null ? new MultiMatchGenerator(generator) : null;
    }

    /**
     * Returns a generator that produces all the matches in the document. It takes a
     * reader as document.
     * 
     * @param reader the reader.
     * @return a generator of matches.
     */
    public MultiMatchGenerator findIter(Reader reader) throws REmatchException {
        cl.rematch.internal.MultiMatchGenerator generator = cppMultiQuery.finditer(reader.cppReader.get());
        return generator != null ? new MultiMatchGenerator(generator) : null;
    }
}
