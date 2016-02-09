package net.egork.generated.collections;

import net.egork.generated.collections.iterator.DoubleIterator;

/**
 * @author Egor Kulikov
 */
public interface DoubleCollection extends DoubleStream {
    //abstract
    public int size();

    //base
    default public boolean isEmpty() {
        return size() == 0;
    }

    default public void add(double value) {
        throw new UnsupportedOperationException();
    }

    default public boolean remove(double value) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            if (value == it.value()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    default public double[] toArray() {
        int size = size();
        double[] array = new double[size];
        int i = 0;
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            array[i++] = it.value();
        }
        return array;
    }

    default public DoubleCollection addAll(DoubleStream values) {
        for (DoubleIterator it = values.doubleIterator(); it.isValid(); it.advance()) {
            add(it.value());
        }
        return this;
    }

    default public DoubleCollection removeAll(DoubleStream values) {
        for (DoubleIterator it = values.doubleIterator(); it.isValid(); it.advance()) {
            remove(it.value());
        }
        return this;
    }

    default public DoubleCollection retainAll(DoubleCollection values) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            if (!values.contains(it.value())) {
                it.remove();
            }
        }
        return this;
    }

    default public DoubleCollection compute() {
        return this;
    }
}
