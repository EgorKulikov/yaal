package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntCharToLongFunction {
    public long value(int first, char second);

    default public IntToLongFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToLongFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
