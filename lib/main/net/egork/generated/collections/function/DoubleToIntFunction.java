package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface DoubleToIntFunction {
    public int value(double key);

    default public DoubleToIntFunction combine(DoubleToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToIntFunction combine(IntToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToIntFunction combine(LongToDoubleFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToIntFunction combine(CharToDoubleFunction f) {
        return val -> value(f.value(val));
    }
}
