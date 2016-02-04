package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntCharToDoubleFunction {
    public double value(int first, char second);

    default public IntToDoubleFunction setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharToDoubleFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
