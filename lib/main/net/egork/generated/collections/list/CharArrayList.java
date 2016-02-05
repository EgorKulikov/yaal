package net.egork.generated.collections.list;

import net.egork.generated.collections.CharAbstractStream;
import net.egork.generated.collections.CharCollection;
import net.egork.generated.collections.CharStream;

import java.util.Arrays;

public class CharArrayList extends CharAbstractStream implements CharList {
    private int size;
    private char[] data;

    public CharArrayList() {
        this(3);
    }

    public CharArrayList(int capacity) {
        data = new char[capacity];
    }

    public CharArrayList(CharCollection c) {
        this(c.size());
        addAll(c);
    }

    public CharArrayList(CharStream c) {
        this();
        if (c instanceof CharCollection) {
            ensureCapacity(((CharCollection) c).size());
        }
        addAll(c);
    }

    public CharArrayList(CharArrayList c) {
        size = c.size();
        data = c.data.clone();
    }

    public CharArrayList(char[] arr) {
        size = arr.length;
        data = arr.clone();
    }

    public int size() {
        return size;
    }

    public char get(int at) {
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

    public void addAt(int index, char value) {
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

    public void set(int index, char value) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
        }
        data[index] = value;
    }
}
