package net.egork.generated.collections.list;

import net.egork.generated.collections.IntAbstractStream;
import net.egork.generated.collections.IntCollection;
import net.egork.generated.collections.IntStream;

import java.util.Arrays;

public class IntArrayList extends IntAbstractStream implements IntList {
    private int size;
    private int[] data;

    public IntArrayList() {
        this(3);
    }

    public IntArrayList(int capacity) {
        data = new int[capacity];
    }

    public IntArrayList(IntCollection c) {
        this(c.size());
        addAll(c);
    }

    public IntArrayList(IntStream c) {
        this();
        if (c instanceof IntCollection) {
            ensureCapacity(((IntCollection) c).size());
        }
        addAll(c);
    }

    public IntArrayList(IntArrayList c) {
        size = c.size();
        data = c.data.clone();
    }

    public IntArrayList(int[] arr) {
        size = arr.length;
        data = arr.clone();
    }

    public int size() {
        return size;
    }

    public int get(int at) {
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

    public void addAt(int index, int value) {
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

    public void set(int index, int value) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
        }
        data[index] = value;
    }
}
