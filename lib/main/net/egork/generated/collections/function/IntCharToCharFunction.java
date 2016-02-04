package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntCharToCharFunction {
    public char value(int first, char second);

    default public IntToCharFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToCharFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
