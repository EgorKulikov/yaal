package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntDoublePredicate {
    public boolean value(int first, double second);

    default public IntFilter setSecond(double second) {
        return v -> value(v, second);
    }

    default public DoubleFilter setFirst(int first) {
        return v -> value(first, v);
    }
}
