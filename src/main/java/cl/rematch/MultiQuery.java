package cl.rematch;

import java.util.ArrayList;
import java.util.List;

public class MultiQuery {

    private final cl.rematch.internal.MultiQuery cppMultiQuery;

    public MultiQuery(String pattern, Flags flags, int maxMempoolDuplications, int maxDeterministicStates,
            int bufferSize) {
        this.cppMultiQuery = new cl.rematch.internal.MultiQuery(pattern, flags.getValue(), maxMempoolDuplications,
                maxDeterministicStates, bufferSize);
    }

    public MultiQuery(String pattern) {
        this(pattern, Flags.none(), Constants.MAX_MEMPOOL_DUPLICATIONS, Constants.MAX_MEMPOOL_DUPLICATIONS,
                Constants.BUFFER_SIZE);
    }

    public MultiQuery(String pattern, Flags flags) {
        this(pattern, flags, Constants.MAX_MEMPOOL_DUPLICATIONS, Constants.MAX_DETERMINISTIC_STATES,
                Constants.BUFFER_SIZE);
    }

    public boolean check(String document) {
        return cppMultiQuery.check(document);
    }

    public boolean check(Reader reader) {
        return cppMultiQuery.check(reader.cppReader.get());
    }

    public String[] variables() {
        cl.rematch.internal.StringVector cppVariables = cppMultiQuery.variables();
        String[] variables = new String[(int) cppVariables.size()];
        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i).toString();
        }
        return variables;
    }

    public MultiMatch findOne(String document) {
        cl.rematch.internal.OptionalMultiMatch match = cppMultiQuery.findone(document);
        if (match.hasValue()) {
            return new MultiMatch(match.value());
        }
        return null;
    }

    public MultiMatch findOne(Reader reader) {
        cl.rematch.internal.OptionalMultiMatch match = cppMultiQuery.findone(reader.cppReader.get());
        if (match.hasValue()) {
            return new MultiMatch(match.value());
        }
        return null;
    }

    public List<MultiMatch> findMany(String document, int limit) {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findmany(document, limit);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public List<MultiMatch> findMany(Reader reader, int limit) {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findmany(reader.cppReader.get(), limit);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public List<MultiMatch> findAll(String document) {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findall(document);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public List<MultiMatch> findAll(Reader reader) {
        cl.rematch.internal.MultiMatchVector matches = cppMultiQuery.findall(reader.cppReader.get());
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public MultiMatchGenerator findIter(String document) {
        cl.rematch.internal.MultiMatchGenerator generator = cppMultiQuery.finditer(document);
        return generator != null ? new MultiMatchGenerator(generator) : null;
    }

    public MultiMatchGenerator findIter(Reader reader) {
        cl.rematch.internal.MultiMatchGenerator generator = cppMultiQuery.finditer(reader.cppReader.get());
        return generator != null ? new MultiMatchGenerator(generator) : null;
    }
}
