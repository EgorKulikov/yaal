package net.egork.generated.collections;

import net.egork.generated.collections.iterator.IntIterator;

/**
 * @author Egor Kulikov
 */
public interface IntCollection extends IntStream {
    //abstract
    public int size();

    //base
    default public boolean isEmpty() {
        return size() == 0;
    }

    default public void add(int value) {
        throw new UnsupportedOperationException();
    }

    default public boolean remove(int value) {
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            if (value == it.value()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    default public int[] toArray() {
        int size = size();
        int[] array = new int[size];
        int i = 0;
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            array[i++] = it.value();
        }
        return array;
    }

    default public IntCollection addAll(IntStream values) {
        for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
            add(it.value());
        }
        return this;
    }

    default public IntCollection removeAll(IntStream values) {
        for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
            remove(it.value());
        }
        return this;
    }

    default public IntCollection retainAll(IntCollection values) {
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            if (!values.contains(it.value())) {
                it.remove();
            }
        }
        return this;
    }

    default public IntCollection compute() {
        return this;
    }
}
