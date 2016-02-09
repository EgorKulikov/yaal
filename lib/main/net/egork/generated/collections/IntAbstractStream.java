package net.egork.generated.collections;

import net.egork.generated.collections.iterator.IntIterator;

public abstract class IntAbstractStream implements IntStream {
    //base
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
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
        if (!(o instanceof IntStream)) {
            return false;
        }
        IntStream c = (IntStream) o;
        IntIterator it = intIterator();
        IntIterator jt = c.intIterator();
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
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            result *= 31;
            result += it.value();
        }
        return result;
    }
}
