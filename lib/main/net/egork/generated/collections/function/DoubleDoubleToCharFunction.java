package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleDoubleToCharFunction {
    public char value(double first, double second);

    default public DoubleToCharFunction setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleToCharFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
