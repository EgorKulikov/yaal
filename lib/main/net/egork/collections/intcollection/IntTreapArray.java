package net.egork.collections.intcollection;

import net.egork.generated.collections.IntAbstractStream;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;

import java.util.Arrays;
import java.util.Random;

/**
 * @author egor@egork.net
 */
public class IntTreapArray extends IntAbstractStream implements IntList {
    private int[] left;
    private int[] right;
    private long[] priority;
    private int[] value;
    private int[] size;
    private int count;
    private Random random = new Random(239);
    private int root = -1;
    private int last = 0;

    public IntTreapArray() {
        createArrays(10);
    }

    public IntTreapArray(int[] array) {
        createArrays(array.length);
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    private void createArrays(int count) {
        left = new int[count];
        right = new int[count];
        priority = new long[count];
        value = new int[count];
        size = new int[count];
    }

    public int get(int at) {
        if (at < 0 || at >= count) {
            throw new IndexOutOfBoundsException();
        }
        int current = root;
        while (true) {
            int leftSize = size(left[current]);
            if (leftSize == at) {
                return value[current];
            }
            if (leftSize > at) {
                current = left[current];
            } else {
                at -= leftSize + 1;
                current = right[current];
            }
        }
    }

    public void set(int at, int value) {
        if (at < 0 || at >= count) {
            throw new IndexOutOfBoundsException();
        }
        int current = root;
        while (true) {
            int leftSize = size(left[current]);
            if (leftSize == at) {
                this.value[current] = value;
                return;
            }
            if (leftSize > at) {
                current = left[current];
            } else {
                at -= leftSize + 1;
                current = right[current];
            }
        }
    }

    public void removeAt(int at) {
        root = remove(root, at);
        count--;
    }

    private int remove(int root, int at) {
        int leftSize = size(left[root]);
        if (leftSize == at) {
            return merge(left[root], right[root]);
        }
        if (leftSize > at) {
            left[root] = remove(left[root], at);
        } else {
            right[root] = remove(right[root], at - leftSize - 1);
        }
        updateSize(root);
        return root;
    }

    private void updateSize(int root) {
        size[root] = size(left[root]) + size(right[root]) + 1;
    }

    private int merge(int cLeft, int cRight) {
        if (cLeft == -1) {
            return cRight;
        }
        if (cRight == -1) {
            return cLeft;
        }
        if (priority[cLeft] > priority[cRight]) {
            right[cLeft] = merge(right[cLeft], cRight);
            updateSize(cLeft);
            return cLeft;
        } else {
            left[cRight] = merge(cLeft, left[cRight]);
            updateSize(cRight);
            return cRight;
        }
    }

    public void putBack(int from, int to) {
        IntIntPair first = split(root, to + 1, 0);
        IntIntPair second = split(first.first, from, 0);
        int right = merge(second.first, first.second);
        root = merge(second.second, right);
    }

    public void addAt(int at, int value) {
        ensureCapacity(last + 1);
        this.value[last] = value;
        priority[last] = random.nextLong();
        left[last] = -1;
        right[last] = -1;
        size[last] = 1;
        IntIntPair result = split(root, at, 0);
        root = merge(result.first, last);
        root = merge(root, result.second);
        count++;
        last++;
    }

    private IntIntPair split(int root, int key, int toAdd) {
        if (root == -1) {
            return new IntIntPair(-1, -1);
        }
        int curKey = toAdd + size(left[root]);
        if (key <= curKey) {
            IntIntPair result = split(left[root], key, toAdd);
            left[root] = result.second;
            updateSize(root);
            return new IntIntPair(result.first, root);
        } else {
            IntIntPair result = split(right[root], key, curKey + 1);
            right[root] = result.first;
            updateSize(root);
            return new IntIntPair(root, result.second);
        }
    }

    private int size(int index) {
        if (index == -1) {
            return 0;
        }
        return size[index];
    }

    private void ensureCapacity(int newSize) {
        if (left.length < newSize) {
            newSize = Math.max(newSize, 2 * count);
            left = Arrays.copyOf(left, newSize);
            right = Arrays.copyOf(right, newSize);
            value = Arrays.copyOf(value, newSize);
            priority = Arrays.copyOf(priority, newSize);
            size = Arrays.copyOf(size, newSize);
        }
    }

    public int size() {
        return count;
    }
}
