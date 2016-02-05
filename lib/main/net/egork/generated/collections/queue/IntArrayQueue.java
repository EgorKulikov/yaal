package net.egork.generated.collections.queue;

import net.egork.generated.collections.IntCollection;
import net.egork.generated.collections.IntStream;
import net.egork.generated.collections.iterator.IntIterator;

import java.util.NoSuchElementException;

public class IntArrayQueue implements IntQueue {
    private int[] data;
    private int from;
    private int to;

    public IntArrayQueue(int capacity) {
        data = new int[Integer.highestOneBit(capacity) << 1];
    }

    public IntArrayQueue() {
        this(3);
    }

    public IntArrayQueue(int[] array) {
        this(array.length);
        to = array.length;
        System.arraycopy(array, 0, data, 0, to);
    }

    public IntArrayQueue(IntStream s) {
        addAll(s);
    }

    public IntArrayQueue(IntCollection c) {
        this(c.size());
        addAll(c);
    }

    public int size() {
        return (to - from) & (data.length - 1);
    }

    public void add(int v) {
        ensureCapacity(size() + 1);
        data[to++] = v;
        to &= data.length - 1;
    }

    public int poll() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        int result = data[from++];
        from &= data.length - 1;
        return result;
    }

    public int peek() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        return data[from];
    }

    public IntIterator intIterator() {
        return new IntIterator() {
            private int at = from;

            public int value() {
                if (at == to) {
                    throw new NoSuchElementException();
                }
                return data[at];
            }

            public boolean advance() {
                if (!isValid()) {
                    throw new NoSuchElementException();
                }
                at++;
                at &= data.length - 1;
                return isValid();
            }

            public boolean isValid() {
                return at != to;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void ensureCapacity(int capacity) {
        if (data.length <= capacity) {
            int[] newData = new int[Integer.highestOneBit(capacity) << 1];
            if (from <= to) {
                System.arraycopy(data, from, newData, 0, size());
            } else {
                System.arraycopy(data, from, newData, 0, data.length - from);
                System.arraycopy(data, 0, newData, data.length - from, to);
            }
            to = size();
            from = 0;
            data = newData;
        }
    }
}
