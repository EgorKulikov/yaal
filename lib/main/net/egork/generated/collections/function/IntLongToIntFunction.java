package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntLongToIntFunction {
    public int value(int first, long second);

    default public IntToIntFunction setSecond(long second) {
        return v -> value(v, second);
    }

    default public LongToIntFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
