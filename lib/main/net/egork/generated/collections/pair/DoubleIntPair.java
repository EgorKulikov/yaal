package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class DoubleIntPair implements Comparable<DoubleIntPair> {
    public final double first;
    public final int second;

    public static DoubleIntPair makePair(double first, int second) {
        return new DoubleIntPair(first, second);
    }

    public DoubleIntPair(double first, int second) {
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

        DoubleIntPair pair = (DoubleIntPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(first);
        result = 31 * result + Integer.hashCode(second);
        return result;
    }

    public IntDoublePair swap() {
        return IntDoublePair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(DoubleIntPair o) {
        int value = Double.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Integer.compare(second, o.second);
    }
}
