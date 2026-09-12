package cl.rematch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mock {
    public record Match(
            Map<String, Span> map) {
        public static Mock.Match of(Map<String, Span> map) {
            return new Mock.Match(map);
        }

        public static Mock.Match of() {
            return new Mock.Match(Map.of());
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
    }

    static public Mock.MultiMatch ToMultiMock(cl.rematch.MultiMatch match) throws REmatchException {
        Map<String, List<Span>> map = new HashMap<>();
        for (String var : match.variables()) {
            map.put(var, match.spans(var));
        }
        return new Mock.MultiMatch(map);
    }
}
