package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongToLongFunction {
    public long value(long key);

    default public DoubleToLongFunction combine(DoubleToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToLongFunction combine(IntToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToLongFunction combine(LongToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToLongFunction combine(CharToLongFunction f) {
        return val -> value(f.value(val));
    }
}
