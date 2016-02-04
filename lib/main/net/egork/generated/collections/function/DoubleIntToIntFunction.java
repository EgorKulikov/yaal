package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleIntToIntFunction {
    public int value(double first, int second);

    default public DoubleToIntFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToIntFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
