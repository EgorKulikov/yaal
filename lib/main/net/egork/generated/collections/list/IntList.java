package net.egork.generated.collections.list;

import net.egork.generated.collections.IntReversableCollection;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.function.IntCharPredicate;
import net.egork.generated.collections.function.IntDoublePredicate;
import net.egork.generated.collections.function.IntFilter;
import net.egork.generated.collections.function.IntIntPredicate;
import net.egork.generated.collections.function.IntLongPredicate;
import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.iterator.IntIterator;

/**
 * @author Egor Kulikov
 */
public interface IntList extends IntReversableCollection {
    public static final IntList EMPTY = new IntArray(new int[0]);

    //abstract
    public abstract int get(int index);

    public abstract void set(int index, int value);

    public abstract void addAt(int index, int value);

    public abstract void removeAt(int index);

    //base
    default public int first() {
        return get(0);
    }

    default public int last() {
        return get(size() - 1);
    }

    default public void swap(int first, int second) {
        if (first == second) {
            return;
        }
        int temp = get(first);
        set(first, get(second));
        set(second, temp);
    }

    default public IntIterator intIterator() {
        return new IntIterator() {
            private int at;
            private boolean removed;

            public int value() {
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

    default public IntIterator reverseIterator() {
        return new IntIterator() {
            private int at = size() - 1;
            private boolean removed;

            public int value() {
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
    default public void add(int value) {
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
        int result = Integer.MAX_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            int current = get(i);
            if (current < result) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public int minIndex(IntComparator comparator) {
        int result = Integer.MIN_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            int current = get(i);
            if (result == Integer.MIN_VALUE || comparator.compare(result, current) > 0) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public int maxIndex() {
        int result = Integer.MIN_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            int current = get(i);
            if (current > result) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public int maxIndex(IntComparator comparator) {
        int result = Integer.MIN_VALUE;
        int size = size();
        int at = -1;
        for (int i = 0; i < size; i++) {
            int current = get(i);
            if (result == Integer.MIN_VALUE || comparator.compare(result, current) < 0) {
                result = current;
                at = i;
            }
        }
        return at;
    }

    default public IntList sort() {
        sort(IntComparator.DEFAULT);
        return this;
    }

    default public IntList sort(IntComparator comparator) {
        Sorter.sort(this, comparator);
        return this;
    }

    default public int find(int value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    default public int find(IntFilter filter) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (filter.accept(get(i))) {
                return i;
            }
        }
        return -1;
    }

    default public int findLast(int value) {
        for (int i = size() - 1; i >= 0; i--) {
            if (get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    default public int findLast(IntFilter filter) {
        for (int i = size() - 1; i >= 0; i--) {
            if (filter.accept(get(i))) {
                return i;
            }
        }
        return -1;
    }

    default public boolean nextPermutation() {
        return nextPermutation(IntComparator.DEFAULT);
    }

    default public boolean nextPermutation(IntComparator comparator) {
        int size = size();
        int last = get(size - 1);
        for (int i = size - 2; i >= 0; i--) {
            int current = get(i);
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

    default IntList unique() {
        int last = Integer.MIN_VALUE;
        IntList result = new IntArrayList();
        int size = size();
        for (int i = 0; i < size; i++) {
            int current = get(i);
            if (current != last) {
                result.add(current);
                last = current;
            }
        }
        return result;
    }

    default int mismatch(IntList l) {
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

    default int mismatch(DoubleList l, IntDoublePredicate p) {
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

    default int mismatch(IntList l, IntIntPredicate p) {
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

    default int mismatch(LongList l, IntLongPredicate p) {
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

    default int mismatch(CharList l, IntCharPredicate p) {
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

    default IntList fill(int value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            set(i, value);
        }
        return this;
    }

    default IntList fill(IntToIntFunction f) {
        int size = size();
        for (int i = 0; i < size; i++) {
            set(i, f.value(i));
        }
        return this;
    }

    default IntList replace(int sample, int value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (get(i) == sample) {
                set(i, value);
            }
        }
        return this;
    }

    default IntList replace(IntFilter f, int value) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (f.accept(get(i))) {
                set(i, value);
            }
        }
        return this;
    }

    default int binarySearch(IntFilter f) {
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

    default int moreOrEqual(int value) {
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

    default int moreOrEqual(int value, IntComparator c) {
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

    default int more(int value) {
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

    default int more(int value, IntComparator c) {
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
    default public IntList subList(final int from, final int to) {
        return new IntList() {
            private final int shift;
            private final int size;

            {
                if (from < 0 || from > to || to > IntList.this.size()) {
                    throw new IndexOutOfBoundsException("from = " + from + ", to = " + to + ", size = " + size());
                }
                shift = from;
                size = to - from;
            }

            public int size() {
                return size;
            }

            public int get(int at) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                return IntList.this.get(at + shift);
            }

            public void addAt(int index, int value) {
                throw new UnsupportedOperationException();
            }

            public void removeAt(int index) {
                throw new UnsupportedOperationException();
            }

            public void set(int at, int value) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                IntList.this.set(at + shift, value);
            }

            public IntList compute() {
                return new IntArrayList(this);
            }
        };
    }
}
