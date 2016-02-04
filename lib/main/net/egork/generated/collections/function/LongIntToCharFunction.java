package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongIntToCharFunction {
    public char value(long first, int second);

    default public LongToCharFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToCharFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
