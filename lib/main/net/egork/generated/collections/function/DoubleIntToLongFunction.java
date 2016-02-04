package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleIntToLongFunction {
    public long value(double first, int second);

    default public DoubleToLongFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToLongFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
