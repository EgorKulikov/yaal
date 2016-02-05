package net.egork.generated.collections.list;

import net.egork.generated.collections.LongAbstractStream;
import net.egork.generated.collections.LongCollection;
import net.egork.generated.collections.LongStream;

import java.util.Arrays;

public class LongArrayList extends LongAbstractStream implements LongList {
    private int size;
    private long[] data;

    public LongArrayList() {
        this(3);
    }

    public LongArrayList(int capacity) {
        data = new long[capacity];
    }

    public LongArrayList(LongCollection c) {
        this(c.size());
        addAll(c);
    }

    public LongArrayList(LongStream c) {
        this();
        if (c instanceof LongCollection) {
            ensureCapacity(((LongCollection) c).size());
        }
        addAll(c);
    }

    public LongArrayList(LongArrayList c) {
        size = c.size();
        data = c.data.clone();
    }

    public LongArrayList(long[] arr) {
        size = arr.length;
        data = arr.clone();
    }

    public int size() {
        return size;
    }

    public long get(int at) {
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

    public void addAt(int index, long value) {
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

    public void set(int index, long value) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
        }
        data[index] = value;
    }
}
