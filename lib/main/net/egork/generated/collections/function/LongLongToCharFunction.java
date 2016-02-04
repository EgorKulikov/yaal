package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongLongToCharFunction {
    public char value(long first, long second);

    default public LongToCharFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToCharFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
