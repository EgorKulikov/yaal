package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharIntPredicate {
    public boolean value(char first, int second);

    default public CharFilter setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntFilter setFirst(char first) {
        return v -> value(first, v);
    }
}
