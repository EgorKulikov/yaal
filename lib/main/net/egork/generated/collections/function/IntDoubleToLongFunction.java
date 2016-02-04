package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntDoubleToLongFunction {
    public long value(int first, double second);

    default public IntToLongFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToLongFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
