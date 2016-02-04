package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntCharPredicate {
    public boolean value(int first, char second);

    default public IntFilter setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharFilter setFirst(int first) {
        return v -> value(first, v);
    }
}
