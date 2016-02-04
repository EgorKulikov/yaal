package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharDoublePredicate {
    public boolean value(char first, double second);

    default public CharFilter setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleFilter setFirst(char first) {
        return v -> value(first, v);
    }
}
