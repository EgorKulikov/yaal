package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntLongToDoubleFunction {
    public double value(int first, long second);

    default public IntToDoubleFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToDoubleFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
