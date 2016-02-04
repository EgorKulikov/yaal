package net.egork.generated.collections.pair;

/**
 * @author Egor Kulikov
 */
public class CharLongPair implements Comparable<CharLongPair> {
    public final char first;
    public final long second;

    public static CharLongPair makePair(char first, long second) {
        return new CharLongPair(first, second);
    }

    public CharLongPair(char first, long second) {
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

        CharLongPair pair = (CharLongPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(first);
        result = 31 * result + Long.hashCode(second);
        return result;
    }

    public LongCharPair swap() {
        return LongCharPair.makePair(second, first);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(CharLongPair o) {
        int value = Character.compare(first, o.first);
        if (value != 0) {
            return value;
        }
        return Long.compare(second, o.second);
    }
}
