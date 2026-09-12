package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class QueryTest {

    record ExceptionCase(String message, String pattern) {
    }

    static Stream<ExceptionCase> exceptionCases() {
        return Stream.of(
                new ExceptionCase("invalid nested variable", "!x{a!x{b}}"),
                new ExceptionCase("unmatched parenthesis", "([a-z]+"),
                new ExceptionCase("unclosed char class", "[0-9"),
                new ExceptionCase("unclosed quantifier", "a{3,5"),
                new ExceptionCase("nothing to repeat", "+abc"),
                new ExceptionCase("invalid quantifier range", "!x{ab{4,3}}"),
                new ExceptionCase("out of order character range", "[z-a]"),
                new ExceptionCase("anchors inside capture", "!x{ab$}"),
                new ExceptionCase("multi spanner in standard query", "!x{a}+"),
                new ExceptionCase("empty capture", "!x{a*}"));
    }

    @ParameterizedTest
    @MethodSource("exceptionCases")
    void testSyntaxException(ExceptionCase testCase) {
        assertThrows(REmatchException.class, () -> {
            new Query(testCase.pattern);
        }, testCase.message);
    }

    @SuppressWarnings("unused")
    @Test
    void testMemoryException() {
        String pattern = "^!x{a}|!x{.+}";
        String document = "a".repeat(200);

        assertThrows(REmatchException.class, () -> {
            var query = new Query(
                    pattern,
                    Flags.none(),
                    0,
                    Constants.MAX_DETERMINISTIC_STATES,
                    Constants.BUFFER_SIZE);
            for (var match : query.findIter(document)) {
            }
        });
    }

    @SuppressWarnings("unused")
    @Test
    void testComplexityException() {
        String pattern = "!x{" + "a".repeat(100) + "}";
        String document = "a".repeat(20000);

        assertThrows(REmatchException.class, () -> {
            var query = new Query(
                    pattern,
                    Flags.none(),
                    Constants.MAX_MEMPOOL_DUPLICATIONS,
                    200,
                    Constants.BUFFER_SIZE);
            for (var match : query.findIter(document)) {
            }
        });
    }

    @Test
    void testVariableLimit() {
        String pattern = IntStream.range(0, 16)
                .mapToObj(i -> "!x{" + i + "}")
                .collect(Collectors.joining());

        assertThrows(REmatchException.class, () -> {
            new Query(pattern);
        });
    }

    @Test
    public void testVariables() {
        String pattern = "!x{!z{a}b}!y{c}";
        var query = new Query(pattern);
        assertEquals(query.variables(), List.of("x", "y", "z"));
    }

    @Test
    void testLbl() {
        String pattern = "!x{.+}";
        String document = "0\n2\n4\n";

        Query query = new Query(pattern, Flags.lineByLine());

        Set<Mock.Match> actual = new HashSet<>();

        for (Match match : query.findIter(document)) {
            actual.add(Mock.ToMock(match));
        }

        Set<Mock.Match> expected = Set.of(
                Mock.Match.of(Map.of("x", new Span(0, 1))),
                Mock.Match.of(Map.of("x", new Span(2, 3))),
                Mock.Match.of(Map.of("x", new Span(4, 5))));
        assertEquals(expected, actual);
    }

    @Test
    void testLblAnchors() {
        String pattern = "^!x{.+}";
        String document = "01\n34\n56";

        Query query = new Query(pattern, Flags.lineByLine());

        Set<Mock.Match> actual = new HashSet<>();

        for (Match match : query.findIter(document)) {
            actual.add(Mock.ToMock(match));
        }

        Set<Mock.Match> expected = Set.of(
                Mock.Match.of(Map.of("x", new Span(0, 1))),
                Mock.Match.of(Map.of("x", new Span(0, 2))),
                Mock.Match.of(Map.of("x", new Span(3, 4))),
                Mock.Match.of(Map.of("x", new Span(3, 5))),
                Mock.Match.of(Map.of("x", new Span(6, 7))),
                Mock.Match.of(Map.of("x", new Span(6, 8))));
        assertEquals(expected, actual);
    }

    @Test
    void testEmptyLines() {
        String pattern = "^!x{.+}";
        String document = "\n\n2";

        Query query = new Query(pattern, Flags.lineByLine());

        Set<Mock.Match> actual = new HashSet<>();

        for (Match match : query.findIter(document)) {
            actual.add(Mock.ToMock(match));
        }

        Set<Mock.Match> expected = Set.of(
                Mock.Match.of(Map.of("x", new Span(2, 3))));
        assertEquals(expected, actual);
    }
}
