package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class DoubleLongPair implements Comparable<DoubleLongPair> {
    public final double first;
    public final long second;

    public static DoubleLongPair makePair(double first, long second) {
        return new DoubleLongPair(first, second);
    }

    public DoubleLongPair(double first, long second) {
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

        DoubleLongPair pair = (DoubleLongPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(first);
        result = 31 * result + Long.hashCode(second);
        return result;
    }

    public LongDoublePair swap() {
        return LongDoublePair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(DoubleLongPair o) {
        int value = Double.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Long.compare(second, o.second);
    }
}
