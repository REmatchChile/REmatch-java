package cl.rematch;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MultiQueryStreamTest {
    Path documentsPath = Path.of("src", "test", "resources", "stream", "multiQuery");

    @Test
    void testLbl() {
        String pattern = "!x{.}!x{.}";

        Path document = documentsPath.resolve("lbl.txt");
        Reader reader = new Reader(document);
        var query = new MultiQuery(pattern, Flags.lineByLine());

        Set<Mock.MultiMatch> actual = new HashSet<>();
        for (MultiMatch match : query.findIter(reader)) {
            actual.add(Mock.ToMultiMock(match));
        }

        Set<Mock.MultiMatch> expected = Set.of(
                Mock.MultiMatch.of(Map.of("x", of(new Span(0, 1), new Span(1, 2)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(3, 4), new Span(4, 5)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(6, 7), new Span(7, 8)))));

        assertEquals(expected, actual);
    }

    @Test
    void testLblAnchors() {
        String pattern = "^!x{.}+";

        Path document = documentsPath.resolve("lbl_anchors.txt");
        Reader reader = new Reader(document);
        var query = new MultiQuery(pattern, Flags.lineByLine());

        Set<Mock.MultiMatch> actual = new HashSet<>();
        for (MultiMatch match : query.findIter(reader)) {
            actual.add(Mock.ToMultiMock(match));
        }

        Set<Mock.MultiMatch> expected = Set.of(
                Mock.MultiMatch.of(Map.of("x", of(new Span(0, 1)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(0, 1), new Span(1, 2)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(3, 4)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(3, 4), new Span(4, 5)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(6, 7)))),
                Mock.MultiMatch.of(Map.of("x", of(new Span(6, 7), new Span(7, 8)))));

        assertEquals(expected, actual);
    }

    @Test
    void testEmptyLines() {
        String pattern = "!x{.}+$";

        Path document = documentsPath.resolve("empty_lines.txt");
        Reader reader = new Reader(document);
        var query = new MultiQuery(pattern, Flags.lineByLine());

        Set<Mock.MultiMatch> actual = new HashSet<>();
        for (MultiMatch match : query.findIter(reader)) {
            actual.add(Mock.ToMultiMock(match));
        }

        Set<Mock.MultiMatch> expected = Set.of(
                Mock.MultiMatch.of(Map.of("x", of(new Span(2, 3)))));
        assertEquals(expected, actual);
    }
}
