package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharCharToCharFunction {
    public char value(char first, char second);

    default public CharToCharFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToCharFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
