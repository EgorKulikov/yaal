package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class KCharPair<K> implements Comparable<KCharPair<K>> {
    public final K first;
    public final char second;

    public static <K> KCharPair<K> makePair(K first, char second) {
        return new KCharPair<K>(first, second);
    }

    private KCharPair(K first, char second) {
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

        CharCharPair pair = (CharCharPair) o;

        return first.equals(pair.first) && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + Character.hashCode(second);
        return result;
    }

    public CharVPair<K> swap() {
        return CharVPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(KCharPair<K> o) {
        int value = ((Comparable<K>) first).compareTo(o.first);
        if (value != 0) {
            return value;
        }
        return Character.compare(second, o.second);
    }
}
