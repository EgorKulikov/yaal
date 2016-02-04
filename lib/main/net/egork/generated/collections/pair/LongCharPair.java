package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class LongCharPair implements Comparable<LongCharPair> {
    public final long first;
    public final char second;

    public static LongCharPair makePair(long first, char second) {
        return new LongCharPair(first, second);
    }

    public LongCharPair(long first, char second) {
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

        LongCharPair pair = (LongCharPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(first);
        result = 31 * result + Character.hashCode(second);
        return result;
    }

    public CharLongPair swap() {
        return CharLongPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(LongCharPair o) {
        int value = Long.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Character.compare(second, o.second);
    }
}
