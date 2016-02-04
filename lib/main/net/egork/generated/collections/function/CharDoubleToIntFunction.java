package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharDoubleToIntFunction {
    public int value(char first, double second);

    default public CharToIntFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToIntFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
