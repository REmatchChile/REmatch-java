package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CheckStreamTest {
    Path documentsPath = Path.of("src", "test", "resources", "stream", "check");

    static Stream<Case.Check> source() {
        return Stream.of(
                new Case.Check(
                        "check true",
                        "^(a+ )*!x{a+} (a+ )*!x{a+}( a+)*$",
                        "check_true",
                        true),
                new Case.Check(
                        "check false",
                        "^(a+ )*!x{a+} (a+ )*!x{a+}( a+)*$",
                        "check_false",
                        false));
    }

    @ParameterizedTest
    @MethodSource("source")
    void testCheck(Case.Check testCase) {
        var query = new MultiQuery(testCase.pattern());

        Path document = documentsPath.resolve(testCase.document() + ".txt");
        Reader reader = new Reader(document);
        boolean actual = query.check(reader);

        assertEquals(testCase.expected(), actual, testCase.name());
    }
}
