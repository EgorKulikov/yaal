package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongCharToIntFunction {
    public int value(long first, char second);

    default public LongToIntFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToIntFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
