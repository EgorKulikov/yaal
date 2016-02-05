package net.egork.generated.collections.queue;

import net.egork.generated.collections.CharCollection;
import net.egork.generated.collections.CharStream;
import net.egork.generated.collections.iterator.CharIterator;

import java.util.NoSuchElementException;

public class CharArrayQueue implements CharQueue {
    private char[] data;
    private int from;
    private int to;

    public CharArrayQueue(int capacity) {
        data = new char[Integer.highestOneBit(capacity) << 1];
    }

    public CharArrayQueue() {
        this(3);
    }

    public CharArrayQueue(int[] array) {
        this(array.length);
        to = array.length;
        System.arraycopy(array, 0, data, 0, to);
    }

    public CharArrayQueue(CharStream s) {
        addAll(s);
    }

    public CharArrayQueue(CharCollection c) {
        this(c.size());
        addAll(c);
    }

    public int size() {
        return (to - from) & (data.length - 1);
    }

    public void add(char v) {
        ensureCapacity(size() + 1);
        data[to++] = v;
        to &= data.length - 1;
    }

    public char poll() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        char result = data[from++];
        from &= data.length - 1;
        return result;
    }

    public char peek() {
        if (from == to) {
            throw new NoSuchElementException();
        }
        return data[from];
    }

    public CharIterator charIterator() {
        return new CharIterator() {
            private int at = from;

            public char value() {
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
            char[] newData = new char[Integer.highestOneBit(capacity) << 1];
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
