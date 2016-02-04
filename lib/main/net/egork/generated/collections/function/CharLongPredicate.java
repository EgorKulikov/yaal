package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharLongPredicate {
    public boolean value(char first, long second);

    default public CharFilter setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongFilter setFirst(char first) {
        return v -> value(first, v);
    }
}
