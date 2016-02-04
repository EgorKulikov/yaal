package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharCharToIntFunction {
    public int value(char first, char second);

    default public CharToIntFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToIntFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
