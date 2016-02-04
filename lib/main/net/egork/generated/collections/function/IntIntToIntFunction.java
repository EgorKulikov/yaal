package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntIntToIntFunction {
    public int value(int first, int second);

    default public IntToIntFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToIntFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
