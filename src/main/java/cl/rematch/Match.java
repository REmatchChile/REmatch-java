package cl.rematch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Match represents a result of a query.
 */
public class Match {
    private final cl.rematch.internal.Match cppMatch;


    /**
     * Creates a new Match. This constructor is intended to be called internally.
     * 
     * @param cppMatch internal match.
     */
    public Match(cl.rematch.internal.Match cppMatch) {
        this.cppMatch = cppMatch;
    }

    /**
     * Retrieves the first index of the span associated to the given variable name.
     * 
     * @param variableName the variable.
     * @return the start index of the span.
     */
    public long start(String variableName) throws REmatchException {
        return this.cppMatch.start(variableName);
    }

    /**
     * Retrieves the first index of the span associated to the given variable id.
     * 
     * @param variableId The variable id.
     * @return The start index of the span.
     */
    public long start(int variableId) throws REmatchException {
        return this.cppMatch.start(variableId);
    }

    /**
     * Retrieves the second index of the span associated to the given variable name.
     * 
     * @param variableName the variable.
     * @return the end index of the span.
     */
    public long end(String variableName) throws REmatchException {
        return this.cppMatch.end(variableName);
    }

    /**
     * Retrieves the second index of the span associated to the given variable id.
     * 
     * @param variableId the variable id.
     * @return the end index of the span.
     */
    public long end(int variableId) throws REmatchException {
        return this.cppMatch.end(variableId);
    }

    /**
     * Retrieves the string captured by the variable.
     * 
     * @param variableName the variable.
     * @return the captured string.
     */
    public String group(String variableName) throws REmatchException {
        return this.cppMatch.group(variableName);
    }

    /**
     * Retrieves the string captured by the variable.
     * 
     * @param variableId the variable id.
     * @return the captured string.
     */
    public String group(int variableId) throws REmatchException {
        return this.cppMatch.group(variableId);
    }

    /**
     * Retrieves the span associated to the given variable id.
     * 
     * @param variableId the variable id.
     * @return the span.
     */
    public Span span(int variableId) throws REmatchException {
        return new Span(this.cppMatch.span(variableId));
    }

    /**
     * Retrieves the span associated to the given variable name.
     * 
     * @param variableName the variable.
     * @return the span.
     */
    public Span span(String variableName) throws REmatchException {
        return new Span(this.cppMatch.span(variableName));
    }

    /**
     * Returns a list containing the variables present in the query.
     * 
     * @return the list of variables.
     */
    public List<String> variables() {
        cl.rematch.internal.StringVector cppVariables = this.cppMatch.variables();
        List<String> variables = new ArrayList<>();
        for (int i = 0; i < cppVariables.size(); i++) {
            variables.add(cppVariables.at(i));
        }
        return variables;
    }

    /**
     * Returns a map that contains the name of the variables as keys and their
     * corresponding spans as values.
     * 
     * @return the map.
     */
    public Map<String, Span> groupdict() {
        cl.rematch.internal.StringSpanMap cppGroupDict = this.cppMatch.groupdict();
        cl.rematch.internal.StringVector vars = this.cppMatch.variables();
        Map<String, Span> groupDict = new HashMap<>();

        for (long i = 0; i < vars.size(); i++) {
            String key = vars.at(i);
            groupDict.put(key, new Span(cppGroupDict.at(key)));
        }
        return groupDict;
    }

    /**
     * Returns true if the match is empty, false otherwise. The match is empty when
     * the query matches the document, but it does not contain any variables.
     * 
     * @return true if the match is empty, false otherwise.
     */
    public boolean empty() {
        return this.cppMatch.empty();
    }

    public String toString() {
        return this.cppMatch.to_string();
    }

}
