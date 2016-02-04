package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class DoubleCharPair implements Comparable<DoubleCharPair> {
    public final double first;
    public final char second;

    public static DoubleCharPair makePair(double first, char second) {
        return new DoubleCharPair(first, second);
    }

    public DoubleCharPair(double first, char second) {
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

        DoubleCharPair pair = (DoubleCharPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(first);
        result = 31 * result + Character.hashCode(second);
        return result;
    }

    public CharDoublePair swap() {
        return CharDoublePair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(DoubleCharPair o) {
        int value = Double.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Character.compare(second, o.second);
    }
}
