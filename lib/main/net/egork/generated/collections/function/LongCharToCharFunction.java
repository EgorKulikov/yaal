package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongCharToCharFunction {
    public char value(long first, char second);

    default public LongToCharFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToCharFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
