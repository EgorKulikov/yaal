package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharLongToIntFunction {
    public int value(char first, long second);

    default public CharToIntFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToIntFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
