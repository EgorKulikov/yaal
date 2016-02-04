package net.egork.generated.collections.function;

/**
 * @author Egor Kulikov
 */
public interface CharFilter {
    public boolean accept(char value);

    default public CharFilter or(CharFilter f) {
        return (val) -> CharFilter.this.accept(val) || f.accept(val);
    }

    default public CharFilter and(CharFilter f) {
        return (val) -> CharFilter.this.accept(val) && f.accept(val);
    }

    default public CharFilter xor(CharFilter f) {
        return (val) -> CharFilter.this.accept(val) ^ f.accept(val);
    }

    default public CharFilter not() {
        return (val) -> !CharFilter.this.accept(val);
    }
}
