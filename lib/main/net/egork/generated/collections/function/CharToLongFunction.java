package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharToLongFunction {
    public long value(char key);

    default public DoubleToLongFunction combine(DoubleToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToLongFunction combine(IntToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToLongFunction combine(LongToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToLongFunction combine(CharToCharFunction f) {
        return val -> value(f.value(val));
    }
}
