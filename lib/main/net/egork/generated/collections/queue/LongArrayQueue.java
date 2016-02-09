package net.egork.generated.collections.queue;

import net.egork.generated.collections.LongCollection;
import net.egork.generated.collections.LongStream;
import net.egork.generated.collections.iterator.LongIterator;

import java.util.NoSuchElementException;

public class LongArrayQueue implements LongQueue {
    private long[] data;
    private int from;
    private int to;

    public LongArrayQueue(int capacity) {
        data = new long[Integer.highestOneBit(capacity) << 1];
    }

    public LongArrayQueue() {
        this(3);
    }

    public LongArrayQueue(int[] array) {
        this(array.length);
        to = array.length;
        System.arraycopy(array, 0, data, 0, to);
    }

    public LongArrayQueue(LongStream s) {
        addAll(s);
    }

    public LongArrayQueue(LongCollection c) {
        this(c.size());
        addAll(c);
    }

    public int size() {
        return (to - from) & (data.length - 1);
    }

    public void add(long v) {
        ensureCapacity(size() + 1);
        data[to++] = v;
        to &= data.length - 1;
    }

    public long poll() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        long result = data[from++];
        from &= data.length - 1;
        return result;
    }

    public long peek() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        return data[from];
    }

    public LongIterator longIterator() {
        return new LongIterator() {
            private int at = from;

            public long value() {
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
            long[] newData = new long[Integer.highestOneBit(capacity) << 1];
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
