package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongIntToIntFunction {
    public int value(long first, int second);

    default public LongToIntFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default public IntToIntFunction setFirst(long first) {
        return v -> value(first, v);
    }
}
