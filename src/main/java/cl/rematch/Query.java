package cl.rematch;

import java.util.ArrayList;
import java.util.List;

/**
 * Query represents a REQL query.
 */
public class Query {

    private final cl.rematch.internal.Query cppQuery;

    /**
     * Creates a new Query.
     * 
     * @param pattern                the REQL query.
     * @param flags                  the flags.
     * @param maxMempoolDuplications the number of duplications.
     * @param maxDeterministicStates the maximum number of deterministic states.
     * @param bufferSize             the buffer size.
     */
    public Query(String pattern, Flags flags, int maxMempoolDuplications, int maxDeterministicStates, int bufferSize) {
        this.cppQuery = new cl.rematch.internal.Query(pattern, flags.getValue(), maxMempoolDuplications,
                maxDeterministicStates,
                bufferSize);
    }

    /**
     * Creates a new Query with the default parameters.
     * 
     * @param pattern the REQL query.
     * @param flags   the flags.
     */
    public Query(String pattern, Flags flags) {
        this(pattern, flags, Constants.MAX_MEMPOOL_DUPLICATIONS, Constants.MAX_DETERMINISTIC_STATES,
                Constants.BUFFER_SIZE);
    }

    /**
     * Creates a new Query with the default flags and parameters.
     * 
     * @param pattern the REQL query.
     */
    public Query(String pattern) {
        this(pattern, Flags.none(), Constants.MAX_MEMPOOL_DUPLICATIONS, Constants.MAX_DETERMINISTIC_STATES,
                Constants.BUFFER_SIZE);
    }

    /**
     * Returns true if there is a match in the document and false otherwise.
     * 
     * @param document the document.
     * @return true if there is a match, false otherwise.
     */
    public boolean check(String document) {
        return cppQuery.check(document);
    }

    /**
     * Returns true if there is a match in the document and false otherwise. It
     * takes a reader as document.
     * 
     * @param reader the reader.
     * @return true if there is a match, false otherwise.
     */
    public boolean check(Reader reader) {
        return cppQuery.check(reader.cppReader.get());
    }

    /**
     * Returns a list containing the variables present in the query.
     * 
     * @return a list of variables.
     */
    public String[] variables() {
        cl.rematch.internal.StringVector cppVariables = this.cppQuery.variables();
        String[] variables = new String[(int) cppVariables.size()];
        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i);
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
    public Match findOne(String document) {
        cl.rematch.internal.OptionalMatch match = cppQuery.findone(document);
        if (match.hasValue()) {
            return new Match(match.value());
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
    public Match findOne(Reader reader) {
        cl.rematch.internal.OptionalMatch match = cppQuery.findone(reader.cppReader.get());
        if (match.hasValue()) {
            return new Match(match.value());
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
    public List<Match> findMany(String document, int limit) {
        cl.rematch.internal.MatchVector cppMatches = cppQuery.findmany(document, limit);
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    /**
     * Returns a list containing up to {@code limit} results from the document. It
     * takes a reader as document.
     * 
     * @param reader the reader.
     * @param limit  the maximum number of matches.
     * @return a list of matches.
     */
    public List<Match> findMany(Reader reader, int limit) {
        cl.rematch.internal.MatchVector cppMatches = cppQuery.findmany(reader.cppReader.get(), limit);
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    /**
     * Returns a list containing all matches found in the document.
     * 
     * @param document the document.
     * @return a list of matches.
     */
    public List<Match> findAll(String document) {
        cl.rematch.internal.MatchVector cppMatches = cppQuery.findall(document);
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    /**
     * Returns a list containing all matches found in the document. It takes a
     * reader as document.
     * 
     * @param reader the reader.
     * @return a list of matches.
     */
    public List<Match> findAll(Reader reader) {
        cl.rematch.internal.MatchVector cppMatches = cppQuery.findall(reader.cppReader.get());
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    /**
     * Returns a generator that produces all the matches in the document.
     * 
     * @param document the document.
     * @return a generator of matches.
     */
    public MatchGenerator findIter(String document) {
        cl.rematch.internal.MatchGenerator generator = cppQuery.finditer(document);
        return generator != null ? new MatchGenerator(generator) : null;
    }

    /**
     * Returns a generator that produces all the matches in the document. It takes a
     * reader as document.
     * 
     * @param reader the reader.
     * @return a generator of matches.
     */
    public MatchGenerator findIter(Reader reader) {
        cl.rematch.internal.MatchGenerator generator = cppQuery.finditer(reader.cppReader.get());
        return generator != null ? new MatchGenerator(generator) : null;
    }
}
