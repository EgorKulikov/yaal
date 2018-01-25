package net.egork.collections.intcollection;

import net.egork.generated.collections.IntAbstractStream;
import net.egork.generated.collections.IntStream;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.generated.collections.set.IntSet;

import java.util.Random;

/**
 * @author egor@egork.net
 */
public class IntTreeSet extends IntAbstractStream implements IntSet {
    private int root;
    private final IntList value = new IntArrayList();
    private final IntList left = new IntArrayList();
    private final IntList right = new IntArrayList();
    private final IntList size = new IntArrayList();
    private final LongList key = new LongArrayList();
    private final IntComparator comparator;
    private final Random random = new Random();
    private int leftSplit;
    private int rightSplit;

    public IntTreeSet() {
        this(IntComparator.DEFAULT);
    }

    public IntTreeSet(IntComparator comparator) {
        this.comparator = comparator;
        value.add(0);
        left.add(0);
        right.add(0);
        size.add(0);
        key.add(Long.MIN_VALUE);
    }

    public IntTreeSet(IntStream stream) {
        this(stream, IntComparator.DEFAULT);
    }

    public IntTreeSet(IntStream stream, IntComparator comparator) {
        this(comparator);
        addAll(stream);
    }

    public IntTreeSet(int[] array) {
        this(array, IntComparator.DEFAULT);
    }

    public IntTreeSet(int[] array, IntComparator comparator) {
        this(comparator);
        addAll(new IntArray(array));
    }

    @Override
    public int size() {
        return getSize(root);
    }

    private int getSize(int root) {
        return size.get(root);
    }

    @Override
    public IntIterator intIterator() {
        return compute().intIterator();
    }

    @Override
    public boolean contains(int val) {
        return has(root, val);
    }

    private boolean has(int root, int val) {
        if (root == 0) {
            return false;
        }
        int compare = comparator.compare(value.get(root), val);
        if (compare == 0) {
            return true;
        } else if (compare > 0) {
            return has(left.get(root), val);
        } else {
            return has(right.get(root), val);
        }
    }

    @Override
    public void add(int val) {
        if (contains(val)) {
            return;
        }
        value.add(val);
        left.add(0);
        right.add(0);
        size.add(1);
        key.add(random.nextLong());
        split(root, val, false);
        root = join(leftSplit, value.size() - 1);
        root = join(root, rightSplit);
    }

    private void split(int root, int val, boolean equalToLeft) {
        if (root == 0) {
            leftSplit = 0;
            rightSplit = 0;
            return;
        }
        int compare = comparator.compare(value.get(root), val);
        if (compare > 0 || compare == 0 && !equalToLeft) {
            split(left.get(root), val, equalToLeft);
            left.set(root, rightSplit);
            rightSplit = root;
        } else {
            split(right.get(root), val, equalToLeft);
            right.set(root, leftSplit);
            leftSplit = root;
        }
        updateSize(root);
    }

    private int join(int lRoot, int rRoot) {
        if (lRoot == 0) {
            return rRoot;
        }
        if (rRoot == 0) {
            return lRoot;
        }
        if (key.get(lRoot) > key.get(rRoot)) {
            right.set(lRoot, join(right.get(lRoot), rRoot));
            updateSize(lRoot);
            return lRoot;
        } else {
            left.set(rRoot, join(lRoot, left.get(rRoot)));
            updateSize(rRoot);
            return rRoot;
        }
    }

    private void updateSize(int root) {
        size.set(root, getSize(left.get(root)) + getSize(right.get(root)) + 1);
    }

    public IntSet subSet(int fromVal, boolean fromIncl, int toVal, boolean toIncl) {
        return new SubTree(fromVal, fromIncl, toVal, toIncl);
    }

    private class SubTree extends IntAbstractStream implements IntSet {
        private int fromVal;
        private boolean fromIncl;
        private int toVal;
        private boolean toIncl;

        private int leftRoot;
        private int centerRoot;
        private int rightRoot;

        private SubTree(int fromVal, boolean fromIncl, int toVal, boolean toIncl) {
            this.fromVal = fromVal;
            this.fromIncl = fromIncl;
            this.toVal = toVal;
            this.toIncl = toIncl;
        }

        @Override
        public int size() {
            prepare();
            int result = getSize(centerRoot);
            rejoin();
            return result;
        }

        private void rejoin() {
            root = IntTreeSet.this.join(leftRoot, IntTreeSet.this.join(centerRoot, rightRoot));
        }

        private void prepare() {
            split(root, fromVal, !fromIncl);
            leftRoot = leftSplit;
            split(rightSplit, toVal, toIncl);
            centerRoot = leftSplit;
            rightRoot = rightSplit;
        }

        @Override
        public void add(int value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public IntIterator intIterator() {
            return compute().intIterator();
        }

        @Override
        public boolean contains(int val) {
            prepare();
            boolean result = has(centerRoot, val);
            rejoin();
            return result;
        }
    }
}
