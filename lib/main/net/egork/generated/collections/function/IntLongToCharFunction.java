package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntLongToCharFunction {
    public char value(int first, long second);

    default public IntToCharFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToCharFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
