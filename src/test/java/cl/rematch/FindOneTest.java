package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindOneTest {
    static Stream<Case.FindOne> source() {
        return Stream.of(
                new Case.FindOne(
                        "simple",
                        "!x{a}",
                        "baba",
                        Mock.Match.of(Map.of("x", new Span(1, 2)))),
                new Case.FindOne(
                        "empty match",
                        "abba",
                        "abba", Mock.Match.of()),
                new Case.FindOne(
                        "no matches",
                        "!x{docx}",
                        "This is a document",
                        null));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testFindOne(Case.FindOne testCase) {
        Query query = new Query(testCase.pattern());

        Match actual = query.findOne(testCase.document());

        if (actual != null) {
            assertEquals(Mock.ToMock(actual), testCase.expected(), testCase.name());
        } else {
            assertNull(actual, testCase.name());
        }
    }
}
