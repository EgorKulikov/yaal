package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class CharCharPair implements Comparable<CharCharPair> {
    public final char first;
    public final char second;

    public static CharCharPair makePair(char first, char second) {
        return new CharCharPair(first, second);
    }

    public CharCharPair(char first, char second) {
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

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(first);
        result = 31 * result + Character.hashCode(second);
        return result;
    }

    public CharCharPair swap() {
        return CharCharPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(CharCharPair o) {
        int value = Character.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Character.compare(second, o.second);
    }
}
