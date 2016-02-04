package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntDoubleToDoubleFunction {
    public double value(int first, double second);

    default public IntToDoubleFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToDoubleFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
