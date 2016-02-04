package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class CharIntPair implements Comparable<CharIntPair> {
    public final char first;
    public final int second;

    public static CharIntPair makePair(char first, int second) {
        return new CharIntPair(first, second);
    }

    public CharIntPair(char first, int second) {
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

        CharIntPair pair = (CharIntPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(first);
        result = 31 * result + Integer.hashCode(second);
        return result;
    }

    public IntCharPair swap() {
        return IntCharPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(CharIntPair o) {
        int value = Character.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Integer.compare(second, o.second);
    }
}
