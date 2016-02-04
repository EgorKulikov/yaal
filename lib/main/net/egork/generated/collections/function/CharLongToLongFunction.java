package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharLongToLongFunction {
    public long value(char first, long second);

    default public CharToLongFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToLongFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
