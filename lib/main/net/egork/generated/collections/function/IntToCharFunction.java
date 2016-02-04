package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface IntToCharFunction {
    public char value(int key);

    default public DoubleToCharFunction combine(DoubleToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToCharFunction combine(IntToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToCharFunction combine(LongToIntFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToCharFunction combine(CharToIntFunction f) {
        return val -> value(f.value(val));
    }
}
