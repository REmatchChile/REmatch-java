package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CheckTest {
    static Stream<Case.Check> source() {
        return Stream.of(
                new Case.Check(
                        "check true",
                        "!x{a}",
                        "abba",
                        true),
                new Case.Check(
                        "check false",
                        "!x{aa}",
                        "abba",
                        false));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testCheck(Case.Check testCase) {
        Query query = new Query(testCase.pattern());

        boolean actual = query.check(testCase.document());

        assertEquals(testCase.expected(), actual, testCase.name());
    }
}
