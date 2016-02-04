package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleIntToCharFunction {
    public char value(double first, int second);

    default public DoubleToCharFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToCharFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
