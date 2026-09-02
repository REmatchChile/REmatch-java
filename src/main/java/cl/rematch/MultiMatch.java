package cl.rematch;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiMatch represents a result of a multi query.
 */
public class MultiMatch {

    private final cl.rematch.internal.MultiMatch cppMultiMatch;

    public MultiMatch(cl.rematch.internal.MultiMatch cppMultiMatch) {
        this.cppMultiMatch = cppMultiMatch;
    }

    /**
     * Returns a vector containing the variables present in the query.
     * 
     * @return a vector of variables.
     */
    public String[] variables() {
        cl.rematch.internal.StringVector cppVariables = cppMultiMatch.variables();
        String[] variables = new String[(int) cppVariables.size()];
        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i).toString();
        }
        return variables;
    }

    /**
     * Retrieves a vector of spans associated to the variable name.
     * 
     * @param variableName the variable.
     * @return a vector of spans.
     */
    public List<cl.rematch.internal.Span> spans(String variableName) {
        cl.rematch.internal.SpanVector spanVec = cppMultiMatch.spans(variableName);
        List<cl.rematch.internal.Span> spans = new ArrayList<>();
        for (int i = 0; i < spanVec.size(); i++) {
            spans.add(spanVec.at(i));
        }
        return spans;
    }

    /**
     * Retrieves a vector of spans associated to the variable id.
     * 
     * @param variableId the variable id.
     * @return a vector of spans.
     */
    public List<cl.rematch.internal.Span> spans(int variableId) {
        cl.rematch.internal.SpanVector spanVec = cppMultiMatch.spans(variableId);
        List<cl.rematch.internal.Span> spans = new ArrayList<>();
        for (int i = 0; i < spanVec.size(); i++) {
            spans.add(spanVec.at(i));
        }
        return spans;
    }

    /**
     * Retrieves a vector of strings captured by the variable name.
     * 
     * @param variableName the variable.
     * @return a vector of strings.
     */
    public List<String> groups(String variableName) {
        cl.rematch.internal.StringVector groupVec = cppMultiMatch.groups(variableName);
        List<String> groups = new ArrayList<>();
        for (int i = 0; i < groupVec.size(); i++) {
            groups.add(groupVec.at(i).toString());
        }
        return groups;
    }

    /**
     * Retrieves a vector of strings captured by the variable.
     * 
     * @param variableId the variable id.
     * @return a vector of strings.
     */
    public List<String> groups(int variableId) {
        cl.rematch.internal.StringVector groupVec = cppMultiMatch.groups(variableId);
        List<String> groups = new ArrayList<>();
        for (int i = 0; i < groupVec.size(); i++) {
            groups.add(groupVec.at(i).toString());
        }
        return groups;
    }

    /**
     * Computes a multi match that contains the spans inside the span passed as argument.
     * 
     * @param span a span.
     * @return a multi match.
     */
    public MultiMatch submatch(cl.rematch.internal.Span span) {
        cl.rematch.internal.MultiMatch sub = cppMultiMatch.submatch(span);
        return new MultiMatch(sub);
    }

    /**
     * Returns true if the match is empty, false otherwise. The match is empty when
     * the query matches the document, but it does not contain any variables.
     * 
     * @return true if the match is empty, false otherwise
     */
    public boolean empty() {
        return cppMultiMatch.empty();
    }

    public String toString() {
        return cppMultiMatch.to_string();
    }
}
