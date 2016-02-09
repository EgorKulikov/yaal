package net.egork.generated.collections;

import net.egork.generated.collections.iterator.DoubleIterator;

public abstract class DoubleAbstractStream implements DoubleStream {
    //base
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
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
        if (!(o instanceof DoubleStream)) {
            return false;
        }
        DoubleStream c = (DoubleStream) o;
        DoubleIterator it = doubleIterator();
        DoubleIterator jt = c.doubleIterator();
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
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            result *= 31;
            result += it.value();
        }
        return result;
    }
}
