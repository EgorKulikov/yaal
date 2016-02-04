package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongDoublePredicate {
    public boolean value(long first, double second);

    default public LongFilter setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleFilter setFirst(long first) {
        return v -> value(first, v);
    }
}
