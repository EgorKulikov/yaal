package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntIntToDoubleFunction {
    public double value(int first, int second);

    default public IntToDoubleFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToDoubleFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
