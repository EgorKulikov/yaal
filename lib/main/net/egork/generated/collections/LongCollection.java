package net.egork.generated.collections;

import net.egork.generated.collections.iterator.LongIterator;

/**
 * @author Egor Kulikov
 */
public interface LongCollection extends LongStream {
    //abstract
    public int size();

    //base
    default public boolean isEmpty() {
        return size() == 0;
    }

    default public void add(long value) {
        throw new UnsupportedOperationException();
    }

    default public boolean remove(long value) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            if (value == it.value()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    default public long[] toArray() {
        int size = size();
        long[] array = new long[size];
        int i = 0;
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            array[i++] = it.value();
        }
        return array;
    }

    default public LongCollection addAll(LongStream values) {
        for (LongIterator it = values.longIterator(); it.isValid(); it.advance()) {
            add(it.value());
        }
        return this;
    }

    default public LongCollection removeAll(LongStream values) {
        for (LongIterator it = values.longIterator(); it.isValid(); it.advance()) {
            remove(it.value());
        }
        return this;
    }

    default public LongCollection retainAll(LongCollection values) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            if (!values.contains(it.value())) {
                it.remove();
            }
        }
        return this;
    }

    default public LongCollection compute() {
        return this;
    }
}
