package cl.rematch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Mock {
    public record Match(
            Map<String, Span> map) {
        public static Mock.Match of(Map<String, Span> map) {
            return new Mock.Match(map);
        }

        public static Mock.Match of() {
            return new Mock.Match(Map.of());
        }

        @Override
        public String toString() {
            List<String> spans = new ArrayList<>();

            for (Entry<String, Span> entry : map.entrySet()) {
                spans.add(String.format("%s: {%s, %s}", entry.getKey(), entry.getValue().first(),
                        entry.getValue().second()));
            }

            return "{" + String.join(", ", spans) + "}";
        }
    }

    static public Mock.Match ToMock(cl.rematch.Match match) throws REmatchException {
        Map<String, Span> map = new HashMap<>();
        for (String var : match.variables()) {
            map.put(var, match.span(var));
        }
        return new Mock.Match(map);
    }

    public record MultiMatch(
            Map<String, List<Span>> map) {
        public static Mock.MultiMatch of(Map<String, List<Span>> map) {
            return new Mock.MultiMatch(map);
        }

        public static Mock.MultiMatch of() {
            return new Mock.MultiMatch(Map.of());
        }

        @Override
        public String toString() {
            List<String> spans = new ArrayList<>();

            for (Entry<String, List<Span>> entry : map.entrySet()) {
                List<String> var_spans = new ArrayList<>();

                for (Span span : entry.getValue()) {
                    var_spans.add(String.format("{%s, %s}", span.first(), span.second()));
                }
                spans.add(entry.getKey() + ": [" + String.join(", ", var_spans) + "]");
            }

            return "{" + String.join(", ", spans) + "}";
        }
    }

    static public Mock.MultiMatch ToMultiMock(cl.rematch.MultiMatch match) throws REmatchException {
        Map<String, List<Span>> map = new HashMap<>();
        for (String var : match.variables()) {
            map.put(var, match.spans(var));
        }
        return new Mock.MultiMatch(map);
    }
}
