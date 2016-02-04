package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class KLongPair<K> implements Comparable<KLongPair<K>> {
    public final K first;
    public final long second;

    public static <K> KLongPair<K> makePair(K first, long second) {
        return new KLongPair<K>(first, second);
    }

    private KLongPair(K first, long second) {
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

        CharLongPair pair = (CharLongPair) o;

        return first.equals(pair.first) && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + Long.hashCode(second);
        return result;
    }

    public LongVPair<K> swap() {
        return LongVPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(KLongPair<K> o) {
        int value = ((Comparable<K>) first).compareTo(o.first);
        if (value != 0) {
            return value;
        }
        return Long.compare(second, o.second);
    }
}
