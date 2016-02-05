package net.egork.generated.collections;

import net.egork.generated.collections.iterator.CharIterator;

/**
 * @author Egor Kulikov
 */
public interface CharCollection extends CharStream {
    //abstract
    public int size();

    //base
    default public boolean isEmpty() {
        return size() == 0;
    }

    default public void add(char value) {
        throw new UnsupportedOperationException();
    }

    default public boolean remove(char value) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (value == it.value()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    default public char[] toArray() {
        int size = size();
        char[] array = new char[size];
        int i = 0;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            array[i++] = it.value();
        }
        return array;
    }

    default public CharCollection addAll(CharStream values) {
        for (CharIterator it = values.charIterator(); it.isValid(); it.advance()) {
            add(it.value());
        }
        return this;
    }

    default public CharCollection removeAll(CharStream values) {
        for (CharIterator it = values.charIterator(); it.isValid(); it.advance()) {
            remove(it.value());
        }
        return this;
    }

    default public CharCollection retainAll(CharCollection values) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (!values.contains(it.value())) {
                it.remove();
            }
        }
        return this;
    }

    default public CharCollection compute() {
        return this;
    }
}
