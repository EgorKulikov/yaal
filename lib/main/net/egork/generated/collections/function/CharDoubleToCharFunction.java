package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharDoubleToCharFunction {
    public char value(char first, double second);

    default public CharToCharFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToCharFunction setFirst(char first) {
        return v -> value(first, v);
    }
}
