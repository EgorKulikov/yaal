package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class CharDoublePair implements Comparable<CharDoublePair> {
    public final char first;
    public final double second;

    public static CharDoublePair makePair(char first, double second) {
        return new CharDoublePair(first, second);
    }

    public CharDoublePair(char first, double second) {
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

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(first);
        result = 31 * result + Double.hashCode(second);
        return result;
    }

    public DoubleCharPair swap() {
        return DoubleCharPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(CharDoublePair o) {
        int value = Character.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Double.compare(second, o.second);
    }
}
