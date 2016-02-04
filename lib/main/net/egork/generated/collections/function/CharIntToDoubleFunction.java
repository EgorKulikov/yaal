package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharIntToDoubleFunction {
    public double value(char first, int second);

    default public CharToDoubleFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToDoubleFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
