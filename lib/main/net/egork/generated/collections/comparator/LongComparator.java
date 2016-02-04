package net.egork.generated.collections.comparator;

/**
 * @author Egor Kulikov
 */
public interface LongComparator {
    public static final LongComparator DEFAULT = (first, second) -> {
        if (first < second) {
            return -1;
        }
        if (first > second) {
            return 1;
        }
        return 0;
    };

    public static final LongComparator REVERSE = (first, second) -> {
        if (first > second) {
            return -1;
        }
        if (first < second) {
            return 1;
        }
        return 0;
    };

    public int compare(long first, long second);
}
