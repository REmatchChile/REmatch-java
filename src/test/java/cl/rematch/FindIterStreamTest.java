package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindIterStreamTest {
    Path documentsPath = Path.of("src", "test", "resources", "stream", "findIter");

    static Stream<Case.FindIter> source() {
        return Stream.of(
                new Case.FindIter(
                        "simple",
                        "!x{a}",
                        "simple",
                        Set.of(
                                Mock.Match.of(Map.of("x", new Span(0, 1))),
                                Mock.Match.of(Map.of("x", new Span(3, 4))))),
                new Case.FindIter(
                        "empty match",
                        "abba",
                        "empty_match",
                        Set.of(
                                Mock.Match.of())),
                new Case.FindIter(
                        "no matches",
                        "!x{docx}",
                        "no_matches",
                        Set.of()));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testFindIter(Case.FindIter testCase) {
        Query query = new Query(testCase.pattern());

        Set<Mock.Match> actual = new HashSet<>();

        Path document = documentsPath.resolve(testCase.document() + ".txt");
        Reader reader = new Reader(document);

        for (Match match : query.findIter(reader)) {
            actual.add(Mock.ToMock(match));
        }

        assertEquals(testCase.expected(), actual, testCase.name());
    }
}
