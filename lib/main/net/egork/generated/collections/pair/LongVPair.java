package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class LongVPair<V> implements Comparable<LongVPair<V>> {
    public final long first;
    public final V second;

    public static <V> LongVPair<V> makePair(long first, V second) {
        return new LongVPair<V>(first, second);
    }

    private LongVPair(long first, V second) {
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

        return first == pair.first && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(first);
        result = 31 * result + second.hashCode();
        return result;
    }

    public KLongPair<V> swap() {
        return KLongPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(LongVPair<V> o) {
        int value = Long.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return ((Comparable<V>) second).compareTo(o.second);
    }
}
