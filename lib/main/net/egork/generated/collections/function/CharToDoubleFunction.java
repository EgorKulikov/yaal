package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharToDoubleFunction {
    public double value(char key);

    default public DoubleToDoubleFunction combine(DoubleToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToDoubleFunction combine(IntToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToDoubleFunction combine(LongToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToDoubleFunction combine(CharToCharFunction f) {
        return val -> value(f.value(val));
    }
}
