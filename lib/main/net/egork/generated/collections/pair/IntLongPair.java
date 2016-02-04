package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class IntLongPair implements Comparable<IntLongPair> {
    public final int first;
    public final long second;

    public static IntLongPair makePair(int first, long second) {
        return new IntLongPair(first, second);
    }

    public IntLongPair(int first, long second) {
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

        IntLongPair pair = (IntLongPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(first);
        result = 31 * result + Long.hashCode(second);
        return result;
    }

    public LongIntPair swap() {
        return LongIntPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(IntLongPair o) {
        int value = Integer.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Long.compare(second, o.second);
    }
}
