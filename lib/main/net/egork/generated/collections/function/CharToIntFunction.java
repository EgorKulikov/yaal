package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharToIntFunction {
    public int value(char key);

    default public DoubleToIntFunction combine(DoubleToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public IntToIntFunction combine(IntToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public LongToIntFunction combine(LongToCharFunction f) {
        return val -> value(f.value(val));
    }

    default public CharToIntFunction combine(CharToCharFunction f) {
        return val -> value(f.value(val));
    }
}
