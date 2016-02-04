package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class CharVPair<V> implements Comparable<CharVPair<V>> {
    public final char first;
    public final V second;

    public static <V> CharVPair<V> makePair(char first, V second) {
        return new CharVPair<V>(first, second);
    }

    private CharVPair(char first, V second) {
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

        return first == pair.first && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(first);
        result = 31 * result + second.hashCode();
        return result;
    }

    public KCharPair<V> swap() {
        return KCharPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(CharVPair<V> o) {
        int value = Character.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return ((Comparable<V>) second).compareTo(o.second);
    }
}
