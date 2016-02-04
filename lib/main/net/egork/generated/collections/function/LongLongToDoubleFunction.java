package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongLongToDoubleFunction {
    public double value(long first, long second);

    default public LongToDoubleFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToDoubleFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
