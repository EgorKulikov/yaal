package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface LongToCharFunction {
    public char value(long key);

    default public DoubleToCharFunction combine(DoubleToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToCharFunction combine(IntToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToCharFunction combine(LongToLongFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToCharFunction combine(CharToLongFunction f) {
        return val -> value(f.value(val));
    }
}
