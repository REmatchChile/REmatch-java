package cl.rematch;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MultiQueryTest {

    @Test
    public void testVariables() {
        String pattern = "!x{!z{a}b}!y{c}!x{a}";
        var query = new MultiQuery(pattern);
        assertEquals(query.variables(), List.of("x", "y", "z"));
    }

    @Test
    void testLbl() {
        String pattern = "!x{.}!x{.}";
        String document = "01\n34\n67\n";

        var query = new MultiQuery(pattern, Flags.lineByLine());

        Set<Mock.MultiMatch> actual = new HashSet<>();
        for (MultiMatch match : query.findIter(document)) {
            actual.add(Mock.ToMultiMock(match));
        }

        Set<Mock.MultiMatch> expected = Set.of(
                Mock.MultiMatch.of(Map.of("x", of(new Span(0, 1), new Span(1, 2)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(3, 4), new Span(4, 5)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(6, 7), new Span(7, 8)))));

        assertEquals(expected, actual);
    }

    @Test
    void testLblAnchors() {
        String pattern = "^!x{.}+";
        String document = "01\n34\n56";

        var query = new MultiQuery(pattern, Flags.lineByLine());

        Set<Mock.MultiMatch> actual = new HashSet<>();
        for (MultiMatch match : query.findIter(document)) {
            actual.add(Mock.ToMultiMock(match));
        }

        Set<Mock.MultiMatch> expected = Set.of(
                Mock.MultiMatch.of(Map.of("x", of(new Span(0, 1)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(0, 1), new Span(1, 2)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(3, 4)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(3, 4), new Span(4, 5)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(6, 7)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(6, 7), new Span(7, 8)))));

        assertEquals(expected, actual);
    }

    @Test
    void testEmptyLines() {
        String pattern = "!x{.}+$";
        String document = "\n\n2";

        var query = new MultiQuery(pattern, Flags.lineByLine());

        Set<Mock.MultiMatch> actual = new HashSet<>();
        for (MultiMatch match : query.findIter(document)) {
            actual.add(Mock.ToMultiMock(match));
        }

        Set<Mock.MultiMatch> expected = Set.of(
                Mock.MultiMatch.of(Map.of("x", of(new Span(2, 3)))));
        assertEquals(expected, actual);
    }
}
