package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleToDoubleFunction {
    public double value(double key);

    default public DoubleToDoubleFunction combine(DoubleToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToDoubleFunction combine(IntToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToDoubleFunction combine(LongToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToDoubleFunction combine(CharToDoubleFunction f) {
        return val -> value(f.value(val));
    }
}
