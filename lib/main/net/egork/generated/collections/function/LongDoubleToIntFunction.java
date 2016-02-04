package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongDoubleToIntFunction {
    public int value(long first, double second);

    default public LongToIntFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToIntFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
