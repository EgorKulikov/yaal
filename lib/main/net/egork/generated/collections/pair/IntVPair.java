package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class IntVPair<V> implements Comparable<IntVPair<V>> {
    public final int first;
    public final V second;

    public static <V> IntVPair<V> makePair(int first, V second) {
        return new IntVPair<V>(first, second);
    }

    private IntVPair(int first, V second) {
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

        IntCharPair pair = (IntCharPair) o;

        return first == pair.first && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(first);
        result = 31 * result + second.hashCode();
        return result;
    }

    public KIntPair<V> swap() {
        return KIntPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(IntVPair<V> o) {
        int value = Integer.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return ((Comparable<V>) second).compareTo(o.second);
    }
}
