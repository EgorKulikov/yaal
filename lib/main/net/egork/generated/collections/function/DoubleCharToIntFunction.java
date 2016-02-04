package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleCharToIntFunction {
    public int value(double first, char second);

    default public DoubleToIntFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToIntFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
