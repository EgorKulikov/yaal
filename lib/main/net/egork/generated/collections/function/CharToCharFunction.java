package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharToCharFunction {
    public char value(char key);

    default public DoubleToCharFunction combine(DoubleToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToCharFunction combine(IntToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToCharFunction combine(LongToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToCharFunction combine(CharToCharFunction f) {
        return val -> value(f.value(val));
    }
}
