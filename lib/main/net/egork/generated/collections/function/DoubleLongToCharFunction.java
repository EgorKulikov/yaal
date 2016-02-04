package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleLongToCharFunction {
    public char value(double first, long second);

    default public DoubleToCharFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToCharFunction setFirst(double first) {
        return v -> value(first, v);
    }
}
