package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleIntPredicate {
    public boolean value(double first, int second);

    default public DoubleFilter setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntFilter setFirst(double first) {
        return v -> value(first, v);
    }
}
