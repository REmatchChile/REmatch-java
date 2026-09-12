package cl.rematch;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiMatch represents a result of a multi query.
 */
public class MultiMatch {

    private final cl.rematch.internal.MultiMatch cppMultiMatch;

    /**
     * Creates a new MultiMatch. This constructor is intended to be called
     * internally.
     * 
     * @param cppMultiMatch internal multi match
     */
    public MultiMatch(cl.rematch.internal.MultiMatch cppMultiMatch) {
        this.cppMultiMatch = cppMultiMatch;
    }

    /**
     * Returns a vector containing the variables present in the query.
     * 
     * @return a vector of variables.
     */
    public List<String> variables() {
        cl.rematch.internal.StringVector cppVariables = cppMultiMatch.variables();
        List<String> variables = new ArrayList<>();
        for (int i = 0; i < cppVariables.size(); i++) {
            variables.add(cppVariables.at(i));
        }
        return variables;
    }

    /**
     * Retrieves a vector of spans associated to the variable name.
     * 
     * @param variableName the variable.
     * @return a vector of spans.
     */
    public List<Span> spans(String variableName) throws REmatchException {
        cl.rematch.internal.SpanVector spanVec = cppMultiMatch.spans(variableName);
        List<Span> spans = new ArrayList<>();
        for (int i = 0; i < spanVec.size(); i++) {
            spans.add(new Span(spanVec.at(i)));
        }
        return spans;
    }

    /**
     * Retrieves a vector of spans associated to the variable id.
     * 
     * @param variableId the variable id.
     * @return a vector of spans.
     */
    public List<Span> spans(int variableId) throws REmatchException {
        cl.rematch.internal.SpanVector spanVec = cppMultiMatch.spans(variableId);
        List<Span> spans = new ArrayList<>();
        for (int i = 0; i < spanVec.size(); i++) {
            spans.add(new Span(spanVec.at(i)));
        }
        return spans;
    }

    /**
     * Retrieves a vector of strings captured by the variable name.
     * 
     * @param variableName the variable.
     * @return a vector of strings.
     */
    public List<String> groups(String variableName) throws REmatchException {
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
    public List<String> groups(int variableId) throws REmatchException {
        cl.rematch.internal.StringVector groupVec = cppMultiMatch.groups(variableId);
        List<String> groups = new ArrayList<>();
        for (int i = 0; i < groupVec.size(); i++) {
            groups.add(groupVec.at(i).toString());
        }
        return groups;
    }

    /**
     * Computes a multi match that contains the spans inside the span passed as
     * argument.
     * 
     * @param span a span.
     * @return a multi match.
     */
    public MultiMatch submatch(Span span) {
        cl.rematch.internal.MultiMatch sub = cppMultiMatch
                .submatch(new cl.rematch.internal.Span(span.first(), span.second()));
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
