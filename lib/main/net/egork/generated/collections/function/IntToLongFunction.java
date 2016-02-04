package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntToLongFunction {
    public long value(int key);

    default public DoubleToLongFunction combine(DoubleToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToLongFunction combine(IntToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToLongFunction combine(LongToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToLongFunction combine(CharToIntFunction f) {
        return val -> value(f.value(val));
    }
}
