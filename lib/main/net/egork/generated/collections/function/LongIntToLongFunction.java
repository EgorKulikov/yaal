package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongIntToLongFunction {
    public long value(long first, int second);

    default public LongToLongFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToLongFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
