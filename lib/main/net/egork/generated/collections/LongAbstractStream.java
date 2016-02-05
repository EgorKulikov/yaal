package net.egork.generated.collections;

import net.egork.generated.collections.iterator.LongIterator;

public abstract class LongAbstractStream implements LongStream {
    //base
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
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
        if (!(o instanceof LongStream)) {
            return false;
        }
        LongStream c = (LongStream) o;
        LongIterator it = longIterator();
        LongIterator jt = c.longIterator();
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
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            result *= 31;
            result += it.value();
        }
        return result;
    }
}
