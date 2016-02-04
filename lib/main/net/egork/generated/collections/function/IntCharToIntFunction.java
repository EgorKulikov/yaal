package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntCharToIntFunction {
    public int value(int first, char second);

    default public IntToIntFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToIntFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
