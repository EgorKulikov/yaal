package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleCharToDoubleFunction {
    public double value(double first, char second);

    default public DoubleToDoubleFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToDoubleFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
