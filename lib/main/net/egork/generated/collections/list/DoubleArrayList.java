package net.egork.generated.collections.list;

import net.egork.generated.collections.DoubleAbstractStream;
import net.egork.generated.collections.DoubleCollection;
import net.egork.generated.collections.DoubleStream;

import java.util.Arrays;

public class DoubleArrayList extends DoubleAbstractStream implements DoubleList {
    private int size;
    private double[] data;

    public DoubleArrayList() {
        this(3);
    }

    public DoubleArrayList(int capacity) {
        data = new double[capacity];
    }

    public DoubleArrayList(DoubleCollection c) {
        this(c.size());
        addAll(c);
    }

    public DoubleArrayList(DoubleStream c) {
        this();
        if (c instanceof DoubleCollection) {
            ensureCapacity(((DoubleCollection) c).size());
        }
        addAll(c);
    }

    public DoubleArrayList(DoubleArrayList c) {
        size = c.size();
        data = c.data.clone();
    }

    public DoubleArrayList(double[] arr) {
        size = arr.length;
        data = arr.clone();
    }

    public int size() {
        return size;
    }

    public double get(int at) {
        if (at >= size) {
            throw new IndexOutOfBoundsException("at = " + at + ", size = " + size);
        }
        return data[at];
    }

    private void ensureCapacity(int capacity) {
        if (data.length >= capacity) {
            return;
        }
        capacity = Math.max(2 * data.length, capacity);
        data = Arrays.copyOf(data, capacity);
    }

    public void addAt(int index, double value) {
        ensureCapacity(size + 1);
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
        }
        if (index != size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = value;
        size++;
    }

    public void removeAt(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
        }
        if (index != size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
    }

    public void set(int index, double value) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
        }
        data[index] = value;
    }
}
