package net.egork.generated.collections.queue;

import net.egork.generated.collections.DoubleCollection;
import net.egork.generated.collections.DoubleStream;
import net.egork.generated.collections.iterator.DoubleIterator;

import java.util.NoSuchElementException;

public class DoubleArrayQueue implements DoubleQueue {
    private double[] data;
    private int from;
    private int to;

    public DoubleArrayQueue(int capacity) {
        data = new double[Integer.highestOneBit(capacity) << 1];
    }

    public DoubleArrayQueue() {
        this(3);
    }

    public DoubleArrayQueue(int[] array) {
        this(array.length);
        to = array.length;
        System.arraycopy(array, 0, data, 0, to);
    }

    public DoubleArrayQueue(DoubleStream s) {
        addAll(s);
    }

    public DoubleArrayQueue(DoubleCollection c) {
        this(c.size());
        addAll(c);
    }

    public int size() {
        return (to - from) & (data.length - 1);
    }

    public void add(double v) {
        ensureCapacity(size() + 1);
        data[to++] = v;
        to &= data.length - 1;
    }

    public double poll() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        double result = data[from++];
        from &= data.length - 1;
        return result;
    }

    public double peek() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        return data[from];
    }

    public DoubleIterator doubleIterator() {
        return new DoubleIterator() {
            private int at = from;

            public double value() {
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
            double[] newData = new double[Integer.highestOneBit(capacity) << 1];
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
