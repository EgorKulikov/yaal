package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntLongToLongFunction {
    public long value(int first, long second);

    default public IntToLongFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToLongFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
