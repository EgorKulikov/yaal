package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleLongToDoubleFunction {
    public double value(double first, long second);

    default public DoubleToDoubleFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToDoubleFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
