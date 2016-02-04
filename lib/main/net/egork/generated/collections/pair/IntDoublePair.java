package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class IntDoublePair implements Comparable<IntDoublePair> {
    public final int first;
    public final double second;

    public static IntDoublePair makePair(int first, double second) {
        return new IntDoublePair(first, second);
    }

    public IntDoublePair(int first, double second) {
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

        IntDoublePair pair = (IntDoublePair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(first);
        result = 31 * result + Double.hashCode(second);
        return result;
    }

    public DoubleIntPair swap() {
        return DoubleIntPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(IntDoublePair o) {
        int value = Integer.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Double.compare(second, o.second);
    }
}
