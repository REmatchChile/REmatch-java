package cl.rematch;

import java.util.HashMap;
import java.util.Map;

/**
 * Match represents a result of a query.
 */
public class Match {
    private final cl.rematch.internal.Match cppMatch;

    public Match(cl.rematch.internal.Match cppMatch) {
        this.cppMatch = cppMatch;
    }

    /**
     * Retrieves the first index of the span associated to the given variable name.
     * 
     * @param variableName the variable.
     * @return the start index of the span.
     */
    public long start(String variableName) {
        return this.cppMatch.start(variableName);
    }

    /**
     * Retrieves the first index of the span associated to the given variable id.
     * 
     * @param variableId The variable id.
     * @return The start index of the span.
     */
    public long start(int variableId) {
        return this.cppMatch.start(variableId);
    }

    /**
     * Retrieves the first index of the span associated to the given variable name.
     * 
     * @param variableName the variable.
     * @return the end index of the span.
     */
    public long end(String variableName) {
        return this.cppMatch.end(variableName);
    }

    /**
     * Retrieves the first index of the span associated to the given variable id.
     * 
     * @param variableId the variable id.
     * @return the end index of the span.
     */
    public long end(int variableId) {
        return this.cppMatch.end(variableId);
    }

    /**
     * Retrieves the string captured by the variable.
     * 
     * @param variableName the variable.
     * @return the captured string.
     */
    public String group(String variableName) {
        return this.cppMatch.group(variableName);
    }

    /**
     * Retrieves the string captured by the variable.
     * 
     * @param variableId the variable id.
     * @return the captured string.
     */
    public String group(int variableId) {
        return this.cppMatch.group(variableId);
    }

    public cl.rematch.internal.Span span(int variableId) {
        return this.cppMatch.span(variableId);
    }

    /**
     * Retrieves the span associated to the given variable name.
     * 
     * @param variableName the variable.
     * @return the span.
     */
    public cl.rematch.internal.Span span(String variableName) {
        return this.cppMatch.span(variableName);
    }

    /**
     * Returns a list containing the variables present in the query.
     * 
     * @return the list of variables.
     */
    public String[] variables() {
        cl.rematch.internal.StringVector cppVariables = this.cppMatch.variables();
        String[] variables = new String[(int) cppVariables.size()];

        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i);
        }

        return variables;
    }

    /**
     * Returns a map that contains the name of the variables as keys and their
     * corresponding spans as values.
     * 
     * @return the map.
     */
    public Map<String, cl.rematch.internal.Span> groupdict() {
        cl.rematch.internal.StringSpanMap cppGroupDict = this.cppMatch.groupdict();
        cl.rematch.internal.StringVector vars = this.cppMatch.variables();
        Map<String, cl.rematch.internal.Span> groupDict = new HashMap<>();

        for (long i = 0; i < vars.size(); i++) {
            String key = vars.at(i).toString();
            cl.rematch.internal.Span span = cppGroupDict.at(key);
            groupDict.put(key, span);
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
