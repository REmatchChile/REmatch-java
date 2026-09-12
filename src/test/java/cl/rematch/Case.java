package cl.rematch;

import java.util.Set;

public class Case {
    public record FindIter(
            String name,
            String pattern,
            String document,
            Set<Mock.Match> expected) {
    }

    public record FindOne(
            String name,
            String pattern,
            String document,
            Mock.Match expected) {
    }

    public record Check(
            String name,
            String pattern,
            String document,
            boolean expected) {
    }

    public record FindIterM(
            String name,
            String pattern,
            String document,
            Set<Mock.MultiMatch> expected) {
    }

    public record FindOneM(
            String name,
            String pattern,
            String document,
            Mock.MultiMatch expected) {
    }
}
