package net.egork.generated.collections.list;

import net.egork.generated.collections.LongAbstractStream;

public class LongArray extends LongAbstractStream implements LongList {
    private long[] data;

    public LongArray(long[] arr) {
        data = arr;
    }

    public int size() {
        return data.length;
    }

    public long get(int at) {
        return data[at];
    }

    public void addAt(int index, long value) {
        throw new UnsupportedOperationException();
    }

    public void removeAt(int index) {
        throw new UnsupportedOperationException();
    }

    public void set(int index, long value) {
        data[index] = value;
    }
}
