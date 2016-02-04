package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongDoubleToLongFunction {
    public long value(long first, double second);

    default public LongToLongFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToLongFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
