package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharCharToDoubleFunction {
    public double value(char first, char second);

    default public CharToDoubleFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToDoubleFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
