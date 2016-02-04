package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class KDoublePair<K> implements Comparable<KDoublePair<K>> {
    public final K first;
    public final double second;

    public static <K> KDoublePair<K> makePair(K first, double second) {
        return new KDoublePair<K>(first, second);
    }

    private KDoublePair(K first, double second) {
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

        CharDoublePair pair = (CharDoublePair) o;

        return first.equals(pair.first) && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + Double.hashCode(second);
        return result;
    }

    public DoubleVPair<K> swap() {
        return DoubleVPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(KDoublePair<K> o) {
        int value = ((Comparable<K>) first).compareTo(o.first);
        if (value != 0) {
            return value;
        }
        return Double.compare(second, o.second);
    }
}
