package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongLongPredicate {
    public boolean value(long first, long second);

    default public LongFilter setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongFilter setFirst(long first) {
        return v -> value(first, v);
    }
}
