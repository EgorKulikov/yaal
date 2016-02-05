package net.egork.generated.collections;

import net.egork.generated.collections.iterator.CharIterator;

public abstract class CharAbstractStream implements CharStream {
    //base
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (first) {
                first = false;
            } else {
                builder.append(' ');
            }
            builder.append(it.value());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CharStream)) {
            return false;
        }
        CharStream c = (CharStream) o;
        CharIterator it = charIterator();
        CharIterator jt = c.charIterator();
        while (it.isValid() && jt.isValid()) {
            if (it.value() != jt.value()) {
                return false;
            }
            it.advance();
            jt.advance();
        }
        return !it.isValid() && !jt.isValid();
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            result *= 31;
            result += it.value();
        }
        return result;
    }
}
