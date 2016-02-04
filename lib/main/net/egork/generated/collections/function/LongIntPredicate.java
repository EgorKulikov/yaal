package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongIntPredicate {
    public boolean value(long first, int second);

    default public LongFilter setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntFilter setFirst(long first) {
        return v -> value(first, v);
    }
}
