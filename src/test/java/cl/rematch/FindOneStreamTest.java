package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindOneStreamTest {
    Path documentsPath = Path.of("src", "test", "resources", "stream", "findOne");

    static Stream<Case.FindOne> source() {
        return Stream.of(
                new Case.FindOne(
                        "simple",
                        "!x{a}",
                        "simple",
                        Mock.Match.of(Map.of("x", new Span(1, 2)))),
                new Case.FindOne(
                        "empty match",
                        "abba",
                        "empty_match", Mock.Match.of()),
                new Case.FindOne(
                        "no matches",
                        "!x{docx}",
                        "no_matches",
                        null));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testFindOne(Case.FindOne testCase) {
        Query query = new Query(testCase.pattern());

        Path document = documentsPath.resolve(testCase.document() + ".txt");
        Reader reader = new Reader(document);
        Match actual = query.findOne(reader);

        if (actual != null) {
            assertEquals(Mock.ToMock(actual), testCase.expected(), testCase.name());
        } else {
            assertNull(actual, testCase.name());
        }
    }
}
