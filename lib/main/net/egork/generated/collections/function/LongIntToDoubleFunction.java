package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongIntToDoubleFunction {
    public double value(long first, int second);

    default public LongToDoubleFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToDoubleFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
