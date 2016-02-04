package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleToLongFunction {
    public long value(double key);

    default public DoubleToLongFunction combine(DoubleToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToLongFunction combine(IntToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToLongFunction combine(LongToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToLongFunction combine(CharToDoubleFunction f) {
        return val -> value(f.value(val));
    }
}
