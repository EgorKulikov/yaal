package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharDoubleToDoubleFunction {
    public double value(char first, double second);

    default public CharToDoubleFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToDoubleFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
