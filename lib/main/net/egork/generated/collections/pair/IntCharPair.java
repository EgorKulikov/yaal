package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class IntCharPair implements Comparable<IntCharPair> {
    public final int first;
    public final char second;

    public static IntCharPair makePair(int first, char second) {
        return new IntCharPair(first, second);
    }

    public IntCharPair(int first, char second) {
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

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(first);
        result = 31 * result + Character.hashCode(second);
        return result;
    }

    public CharIntPair swap() {
        return CharIntPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(IntCharPair o) {
        int value = Integer.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Character.compare(second, o.second);
    }
}
