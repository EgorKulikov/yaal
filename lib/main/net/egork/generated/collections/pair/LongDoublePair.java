package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class LongDoublePair implements Comparable<LongDoublePair> {
    public final long first;
    public final double second;

    public static LongDoublePair makePair(long first, double second) {
        return new LongDoublePair(first, second);
    }

    public LongDoublePair(long first, double second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LongDoublePair pair = (LongDoublePair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(first);
        result = 31 * result + Double.hashCode(second);
        return result;
    }

    public DoubleLongPair swap() {
        return DoubleLongPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(LongDoublePair o) {
        int value = Long.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Double.compare(second, o.second);
    }
}
