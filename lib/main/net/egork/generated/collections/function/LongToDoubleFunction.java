package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongToDoubleFunction {
    public double value(long key);

    default public DoubleToDoubleFunction combine(DoubleToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToDoubleFunction combine(IntToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToDoubleFunction combine(LongToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToDoubleFunction combine(CharToLongFunction f) {
        return val -> value(f.value(val));
    }
}
