package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharCharPredicate {
    public boolean value(char first, char second);

    default public CharFilter setSecond(char second) {
        return v -> value(v, second);
    }

    default public CharFilter setFirst(char first) {
        return v -> value(first, v);
    }
}
