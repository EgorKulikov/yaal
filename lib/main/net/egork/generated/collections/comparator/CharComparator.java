package net.egork.generated.collections.comparator;

/**
 * @author Egor Kulikov
 */
public interface CharComparator {
    public static final CharComparator DEFAULT = (first, second) -> {
        if (first < second) {
            return -1;
        }
        if (first > second) {
            return 1;
        }
        return 0;
    };

    public static final CharComparator REVERSE = (first, second) -> {
        if (first > second) {
            return -1;
        }
        if (first < second) {
            return 1;
        }
        return 0;
    };

    public int compare(char first, char second);
}
