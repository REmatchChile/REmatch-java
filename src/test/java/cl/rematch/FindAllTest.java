package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindAllTest {
    static Stream<Case.FindIter> source() {
        return Stream.of(
                new Case.FindIter(
                        "simple",
                        "(^| )!word{(\\w|[\\-'])+}([ ,.])",
                        "You don't know about me.",
                        Set.of(
                                Mock.Match.of(Map.of("word", new Span(0, 3))),
                                Mock.Match.of(Map.of("word", new Span(4, 9))),
                                Mock.Match.of(Map.of("word", new Span(10, 14))),
                                Mock.Match.of(Map.of("word", new Span(15, 20))),
                                Mock.Match.of(Map.of("word", new Span(21, 23))))));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testFindAll(Case.FindIter testCase) {
        Query query = new Query(testCase.pattern());

        List<Match> matches = query.findAll(testCase.document());

        Set<Mock.Match> actual = new HashSet<>();

        for (Match m : matches) {
            actual.add(Mock.ToMock(m));
        }

        assertEquals(testCase.expected(), actual, testCase.name());
    }
}
