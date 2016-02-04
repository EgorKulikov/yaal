package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleDoubleToIntFunction {
    public int value(double first, double second);

    default public DoubleToIntFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToIntFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
