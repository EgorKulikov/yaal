package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntToDoubleFunction {
    public double value(int key);

    default public DoubleToDoubleFunction combine(DoubleToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToDoubleFunction combine(IntToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToDoubleFunction combine(LongToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToDoubleFunction combine(CharToIntFunction f) {
        return val -> value(f.value(val));
    }
}
