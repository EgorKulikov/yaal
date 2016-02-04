package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharIntToCharFunction {
    public char value(char first, int second);

    default public CharToCharFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToCharFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
