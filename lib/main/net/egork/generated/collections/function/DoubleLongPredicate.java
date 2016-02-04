package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleLongPredicate {
    public boolean value(double first, long second);

    default public DoubleFilter setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongFilter setFirst(double first) {
        return v -> value(first, v);
    }
}
