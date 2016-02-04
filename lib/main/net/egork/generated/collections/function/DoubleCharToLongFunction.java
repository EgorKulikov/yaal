package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleCharToLongFunction {
    public long value(double first, char second);

    default public DoubleToLongFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToLongFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
