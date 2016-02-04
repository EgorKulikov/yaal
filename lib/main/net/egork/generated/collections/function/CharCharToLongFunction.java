package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharCharToLongFunction {
    public long value(char first, char second);

    default public CharToLongFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToLongFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
