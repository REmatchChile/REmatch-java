package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindManyTest {
    static Stream<Case.FindMany> source() {
        return Stream.of(
                new Case.FindMany(
                        "simple",
                        "(^| )!word{(\\w|[\\-'])+}([ ,.])",
                        "You don't know about me.",
                        3,
                        Set.of(
                                Mock.Match.of(Map.of("word", new Span(0, 3))),
                                Mock.Match.of(Map.of("word", new Span(4, 9))),
                                Mock.Match.of(Map.of("word", new Span(10, 14))))));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testFindMany(Case.FindMany testCase) {
        Query query = new Query(testCase.pattern());

        List<Match> matches = query.findMany(testCase.document(), testCase.limit());

        Set<Mock.Match> actual = new HashSet<>();

        for (Match m : matches) {
            actual.add(Mock.ToMock(m));
        }

        assertEquals(testCase.expected(), actual, testCase.name());
    }
}
