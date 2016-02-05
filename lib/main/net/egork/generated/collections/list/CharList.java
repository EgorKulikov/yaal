package net.egork.generated.collections.list;

import net.egork.generated.collections.CharReversableCollection;
import net.egork.generated.collections.comparator.CharComparator;
import net.egork.generated.collections.function.CharCharPredicate;
import net.egork.generated.collections.function.CharDoublePredicate;
import net.egork.generated.collections.function.CharFilter;
import net.egork.generated.collections.function.CharIntPredicate;
import net.egork.generated.collections.function.CharLongPredicate;
import net.egork.generated.collections.function.IntToCharFunction;
import net.egork.generated.collections.iterator.CharIterator;

/**
 * @author Egor Kulikov
 */
public interface CharList extends CharReversableCollection {
    public static final CharList EMPTY = new CharArray(new char[0]);

    //abstract
    public abstract char get(int index);

    public abstract void set(int index, char value);

    public abstract void addAt(int index, char value);

    public abstract void removeAt(int index);

    //base
    default public char first() {
        return get(0);
    }

    default public char last() {
        return get(size() - 1);
    }

    default public void swap(int first, int second) {
        if (first == second) {
            return;
        }
        char temp = get(first);
        set(first, get(second));
        set(second, temp);
    }

    default public CharIterator charIterator() {
        return new CharIterator() {
            private int at;
            private boolean removed;

            public char value() {
                if (removed) {
                    throw new IllegalStateException();
                }
                return get(at);
            }

            public boolean advance() {
                at++;
                removed = false;
                return isValid();
            }

            public boolean isValid() {
                return !removed && at < size();
            }

            public void remove() {
                removeAt(at);
                at--;
                removed = true;
            }
        };
    }

    default public CharIterator reverseIterator() {
        return new CharIterator() {
            private int at = size() - 1;
            private boolean removed;

            public char value() {
                if (removed) {
                    throw new IllegalStateException();
                }
                return get(at);
            }

            public boolean advance() {
                at--;
                removed = false;
                return isValid();
            }

            public boolean isValid() {
                return !removed && at >= 0;
            }

            public void remove() {
                removeAt(at);
                removed = true;
            }
        };
    }

    @Override
    default public void add(char value) {
        addAt(size(), value);
    }

    default public void popLast() {
        removeAt(size() - 1);
    }

    default public void popFirst() {
        removeAt(0);
    }

    //algorithms
    default public int minIndex() {
        char result = Character.MAX_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            char current = get(i);
            if (current < result) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public int minIndex(CharComparator comparator) {
        char result = Character.MAX_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            char current = get(i);
            if (result == Character.MAX_VALUE || comparator.compare(result, current) > 0) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public int maxIndex() {
        char result = Character.MIN_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            char current = get(i);
            if (current > result) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public int maxIndex(CharComparator comparator) {
        char result = Character.MAX_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            char current = get(i);
            if (result == Character.MAX_VALUE || comparator.compare(result, current) < 0) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public CharList sort() {
        sort(CharComparator.DEFAULT);
        return this;
    }

    default public CharList sort(CharComparator comparator) {
        Sorter.sort(this, comparator);
        return this;
    }

    default public int find(char value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    default public int find(CharFilter filter) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (filter.accept(get(i))) {
                return i;
            }
        }
        return -1;
    }

    default public int findLast(char value) {
        for (int i = size() - 1; i >= 0; i--) {
            if (get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    default public int findLast(CharFilter filter) {
        for (int i = size() - 1; i >= 0; i--) {
            if (filter.accept(get(i))) {
                return i;
            }
        }
        return -1;
    }

    default public boolean nextPermutation() {
        return nextPermutation(CharComparator.DEFAULT);
    }

    default public boolean nextPermutation(CharComparator comparator) {
        int size = size();
        char last = get(size - 1);
        for (int i = size - 2; i >= 0; i--) {
            char current = get(i);
            if (comparator.compare(last, current) > 0) {
                for (int j = size - 1; j > i; j--) {
                    if (comparator.compare(get(j), current) > 0) {
                        swap(i, j);
                        subList(i + 1, size).inPlaceReverse();
                        return true;
                    }
                }
            }
            last = current;
        }
        return false;
    }

    default public void inPlaceReverse() {
        for (int i = 0, j = size() - 1; i < j; i++, j--) {
            swap(i, j);
        }
    }

    default CharList unique() {
        char last = Character.MAX_VALUE;
        CharList result = new CharArrayList();
        int size = size();
        for (int i = 0; i < size; i++) {
            char current = get(i);
            if (current != last) {
                result.add(current);
                last = current;
            }
        }
        return result;
    }

    default int mismatch(CharList l) {
        int size = Math.min(size(), l.size());
        for (int i = 0; i < size; i++) {
            if (get(i) != l.get(i)) {
                return i;
            }
        }
        if (size() != l.size()) {
            return size;
        }
        return -1;
    }

    default int mismatch(DoubleList l, CharDoublePredicate p) {
        int size = Math.min(size(), l.size());
        for (int i = 0; i < size; i++) {
            if (!p.value(get(i), l.get(i))) {
                return i;
            }
        }
        if (size() != l.size()) {
            return size;
        }
        return -1;
    }

    default int mismatch(IntList l, CharIntPredicate p) {
        int size = Math.min(size(), l.size());
        for (int i = 0; i < size; i++) {
            if (!p.value(get(i), l.get(i))) {
                return i;
            }
        }
        if (size() != l.size()) {
            return size;
        }
        return -1;
    }

    default int mismatch(LongList l, CharLongPredicate p) {
        int size = Math.min(size(), l.size());
        for (int i = 0; i < size; i++) {
            if (!p.value(get(i), l.get(i))) {
                return i;
            }
        }
        if (size() != l.size()) {
            return size;
        }
        return -1;
    }

    default int mismatch(CharList l, CharCharPredicate p) {
        int size = Math.min(size(), l.size());
        for (int i = 0; i < size; i++) {
            if (!p.value(get(i), l.get(i))) {
                return i;
            }
        }
        if (size() != l.size()) {
            return size;
        }
        return -1;
    }

    default CharList fill(char value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            set(i, value);
        }
        return this;
    }

    default CharList fill(IntToCharFunction f) {
        int size = size();
        for (int i = 0; i < size; i++) {
            set(i, f.value(i));
        }
        return this;
    }

    default CharList replace(char sample, char value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (get(i) == sample) {
                set(i, value);
            }
        }
        return this;
    }

    default CharList replace(CharFilter f, char value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (f.accept(get(i))) {
                set(i, value);
            }
        }
        return this;
    }

    default int binarySearch(CharFilter f) {
        int left = 0;
        int right = size();
        while (left < right) {
            int middle = (left + right) >> 1;
            if (f.accept(get(middle))) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    default int moreOrEqual(char value) {
        int left = 0;
        int right = size();
        while (left < right) {
            int middle = (left + right) >> 1;
            if (value <= get(middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    default int moreOrEqual(char value, CharComparator c) {
        int left = 0;
        int right = size();
        while (left < right) {
            int middle = (left + right) >> 1;
            if (c.compare(value, get(middle)) <= 0) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    default int more(char value) {
        int left = 0;
        int right = size();
        while (left < right) {
            int middle = (left + right) >> 1;
            if (value < get(middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    default int more(char value, CharComparator c) {
        int left = 0;
        int right = size();
        while (left < right) {
            int middle = (left + right) >> 1;
            if (c.compare(value, get(middle)) < 0) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    //views
    default public CharList subList(final int from, final int to) {
        return new CharList() {
            private final int shift;
            private final int size;

            {
                if (from < 0 || from > to || to > CharList.this.size()) {
                    throw new IndexOutOfBoundsException("from = " + from + ", to = " + to + ", size = " + size());
                }
                shift = from;
                size = to - from;
            }

            public int size() {
                return size;
            }

            public char get(int at) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                return CharList.this.get(at + shift);
            }

            public void addAt(int index, char value) {
                throw new UnsupportedOperationException();
            }

            public void removeAt(int index) {
                throw new UnsupportedOperationException();
            }

            public void set(int at, char value) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                CharList.this.set(at + shift, value);
            }

            public CharList compute() {
                return new CharArrayList(this);
            }
        };
    }
}
