package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindIterTest {
    static Stream<Case.FindIter> source() {
        return Stream.of(
                new Case.FindIter(
                        "simple",
                        "!x{a}",
                        "abba",
                        Set.of(
                                Mock.Match.of(Map.of("x", new Span(0, 1))),
                                Mock.Match.of(Map.of("x", new Span(3, 4))))),
                new Case.FindIter(
                        "empty match",
                        "abba",
                        "abba",
                        Set.of(
                                Mock.Match.of())),
                new Case.FindIter(
                        "no matches",
                        "!x{docx}",
                        "This is a document",
                        Set.of()));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testFinditer(Case.FindIter testCase) {
        Query query = new Query(testCase.pattern());

        Set<Mock.Match> actual = new HashSet<>();

        for (Match match : query.findIter(testCase.document())) {
            actual.add(Mock.ToMock(match));
        }

        assertEquals(testCase.expected(), actual, testCase.name());
    }
}
