package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongCharToDoubleFunction {
    public double value(long first, char second);

    default public LongToDoubleFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToDoubleFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
