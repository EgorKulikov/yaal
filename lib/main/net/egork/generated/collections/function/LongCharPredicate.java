package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongCharPredicate {
    public boolean value(long first, char second);

    default public LongFilter setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharFilter setFirst(long first) {
        return v -> value(first, v);
    }
}
