package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongLongToIntFunction {
    public int value(long first, long second);

    default public LongToIntFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToIntFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
