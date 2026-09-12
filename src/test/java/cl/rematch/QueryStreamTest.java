package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class QueryStreamTest {
    Path documentsPath = Path.of("src", "test", "resources", "stream", "query");

    @Test
    void testLbl() {
        String pattern = "!x{.+}";

        Path document = documentsPath.resolve("lbl.txt");
        Reader reader = new Reader(document);
        var query = new Query(pattern, Flags.lineByLine());

        Set<Mock.Match> actual = new HashSet<>();
        for (Match match : query.findIter(reader)) {
            actual.add(Mock.ToMock(match));
        }

        Set<Mock.Match> expected = Set.of(
                Mock.Match.of(Map.of("x", new Span(0L, 1L))),
                Mock.Match.of(Map.of("x", new Span(2L, 3L))),
                Mock.Match.of(Map.of("x", new Span(4L, 5L))));
        assertEquals(expected, actual);
    }

    @Test
    void testLblAnchors() {
        String pattern = "^!x{.+}";

        Path document = documentsPath.resolve("lbl_anchors.txt");
        Reader reader = new Reader(document);
        var query = new Query(pattern, Flags.lineByLine());

        Set<Mock.Match> actual = new HashSet<>();
        for (Match match : query.findIter(reader)) {
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

        Path document = documentsPath.resolve("empty_lines.txt");
        Reader reader = new Reader(document);
        var query = new Query(pattern, Flags.lineByLine());

        Set<Mock.Match> actual = new HashSet<>();
        for (Match match : query.findIter(reader)) {
            actual.add(Mock.ToMock(match));
        }

        Set<Mock.Match> expected = Set.of(
                Mock.Match.of(Map.of("x", new Span(2, 3))));
        assertEquals(expected, actual);
    }
}
