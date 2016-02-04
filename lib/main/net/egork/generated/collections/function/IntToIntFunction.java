package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntToIntFunction {
    public int value(int key);

    default public DoubleToIntFunction combine(DoubleToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToIntFunction combine(IntToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToIntFunction combine(LongToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToIntFunction combine(CharToIntFunction f) {
        return val -> value(f.value(val));
    }
}
