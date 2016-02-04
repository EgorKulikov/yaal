package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntDoubleToCharFunction {
    public char value(int first, double second);

    default public IntToCharFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToCharFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
