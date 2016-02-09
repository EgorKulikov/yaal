package net.egork.generated.collections.list;

import net.egork.generated.collections.CharAbstractStream;

public class CharArray extends CharAbstractStream implements CharList {
    private char[] data;

    public CharArray(char[] arr) {
        data = arr;
    }

    public int size() {
        return data.length;
    }

    public char get(int at) {
        return data[at];
    }

    public void addAt(int index, char value) {
        throw new UnsupportedOperationException();
    }

    public void removeAt(int index) {
        throw new UnsupportedOperationException();
    }

    public void set(int index, char value) {
        data[index] = value;
    }
}
