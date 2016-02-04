package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntDoubleToIntFunction {
    public int value(int first, double second);

    default public IntToIntFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToIntFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
