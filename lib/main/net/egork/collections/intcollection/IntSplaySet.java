package net.egork.collections.intcollection;

import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.set.IntSet;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author egor@egork.net
 */
public class IntSplaySet implements IntSet {
    protected int[] value;
    protected int[] left;
    protected int[] right;
    protected int[] parent;
    private int[] size;
    private final IntComparator comparator;
    private int nextFree;

    protected int root = -1;

    public IntSplaySet() {
        this(null);
    }

    public IntSplaySet(IntComparator comparator) {
        this(comparator, 5);
    }

    public IntSplaySet(int capacity) {
        this(null, capacity);
    }

    public IntSplaySet(IntComparator comparator, int capacity) {
        this.comparator = comparator;
        value = new int[capacity];
        left = new int[capacity];
        right = new int[capacity];
        parent = new int[capacity];
        size = new int[capacity];
    }

    protected final int compare(int first, int second) {
        if (comparator == null) {
            return first < second ? -1 : first == second ? 0 : 1;
        } else {
            return comparator.compare(first, second);
        }
    }

    @Override
    public IntIterator intIterator() {
        return new IntIterator() {
            private boolean first = true;
            private boolean exhausted = root == -1;

            @Override
            public int value() throws NoSuchElementException {
                if (first) {
                    if (root == -1) {
                        throw new NoSuchElementException();
                    }
                    int leftmost = leftmost(root);
                    splay(leftmost);
                    first = false;
                }
                return value[root];
            }

            @Override
            public boolean advance() throws NoSuchElementException {
                if (root == -1 || exhausted) {
                    throw new NoSuchElementException();
                }
                int next = leftmost(right[root]);
                if (next == -1) {
                    exhausted = true;
                    return false;
                }
                splay(next);
                return true;
            }

            @Override
            public boolean isValid() {
                return !exhausted;
            }

            @Override
            public void remove() throws NoSuchElementException {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void splay(int node) {
        if (node == -1) {
            return;
        }
        while (parent[node] != -1) {
            if (parent[parent[node]] == -1) {
                rotate(node);
            } else if ((left[parent[node]] == node) == (left[parent[parent[node]]] == parent[node])) {
                rotate(parent[node]);
                rotate(node);
            } else {
                rotate(node);
                rotate(node);
            }
        }
        this.root = node;
    }

    private void rotate(int node) {
        int cParent = parent[node];
        if (node == left[cParent]) {
            setLeft(cParent, right[node]);
            if (parent[cParent] != -1) {
                if (cParent == left[parent[cParent]]) {
                    setLeft(parent[cParent], node);
                } else {
                    setRight(parent[cParent], node);
                }
            } else {
                parent[node] = -1;
            }
            setRight(node, cParent);
        } else {
            setRight(cParent, left[node]);
            if (parent[cParent] != -1) {
                if (cParent == left[parent[cParent]]) {
                    setLeft(parent[cParent], node);
                } else {
                    setRight(parent[cParent], node);
                }
            } else {
                parent[node] = -1;
            }
            setLeft(node, cParent);
        }
        update(cParent);
        update(node);
    }

    protected void update(int node) {
        size[node] = size(left[node]) + size(right[node]) + 1;
    }

    protected int size(int node) {
        if (node != -1) {
            return size[node];
        }
        return 0;
    }

    protected int leftmost(int root) {
        if (root == -1) {
            return -1;
        }
        while (left[root] != -1) {
            root = left[root];
        }
        return root;
    }

    protected int rightmost(int root) {
        if (root == -1) {
            return -1;
        }
        while (right[root] != -1) {
            root = right[root];
        }
        return root;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void add(int value) {
        if (contains(value)) {
            return;
        }
        ensureCapacity(nextFree + 1);
        Split roots = split(root, value);
        setLeft(nextFree, roots.left);
        setRight(nextFree, roots.right);
        parent[nextFree] = -1;
        this.value[nextFree] = value;
        update(nextFree);
        root = nextFree++;
    }

    private void merge(int left, int right) {
        if (left == -1) {
            root = right;
        } else if (right == -1) {
            root = left;
        } else {
            splay(rightmost(left));
            setRight(root, right);
            update(root);
        }
    }

    private void setLeft(int node, int child) {
        left[node] = child;
        if (child != -1) {
            parent[child] = node;
        }
    }

    private void setRight(int node, int child) {
        right[node] = child;
        if (child != -1) {
            parent[child] = node;
        }
    }

    protected Split split(int root, int value) {
        if (root == -1) {
            return new Split();
        }
        int compare = compare(this.value[root], value);
        if (compare < 0) {
            Split result = split(right[root], value);
            setRight(root, result.left);
            result.left = root;
            update(root);
            behead(root);
            return result;
        } else if (compare > 0) {
            Split result = split(left[root], value);
            setLeft(root, result.right);
            result.right = root;
            update(root);
            behead(root);
            return result;
        } else {
            Split result = new Split();
            result.left = left[root];
            result.right = right[root];
            behead(left[root]);
            behead(right[root]);
            return result;
        }
    }

    protected int ensureCapacity(int capacity) {
        if (parent.length < capacity) {
            capacity = Math.max(2 * parent.length, capacity);
            left = Arrays.copyOf(left, capacity);
            right = Arrays.copyOf(right, capacity);
            parent = Arrays.copyOf(parent, capacity);
            size = Arrays.copyOf(size, capacity);
            value = Arrays.copyOf(value, capacity);
            return capacity;
        }
        return parent.length;
    }

    @Override
    public boolean remove(int value) {
        if (contains(value)) {
            behead(left[root]);
            behead(right[root]);
            merge(left[root], right[root]);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(int value) {
        int node = root;
        int lastNode = -1;
        while (node != -1) {
            lastNode = node;
            int compare = compare(value, this.value[node]);
            if (compare == 0) {
                splay(node);
                return true;
            }
            if (compare < 0) {
                node = left[node];
            } else {
                node = right[node];
            }
        }
        if (lastNode != -1) {
            splay(lastNode);
        }
        return false;
    }

    protected void behead(int node) {
        if (node != -1) {
            parent[node] = -1;
        }
    }

    public int indexOf(int value) {
        if (!contains(value)) {
            return -1;
        }
        return size(left[root]);
    }

    public int subSetSize(int fromValue, boolean includeFrom, int toValue, boolean includeTo) {
        return headSetSize(toValue, includeTo) - headSetSize(fromValue, !includeFrom);
    }

    public int headSetSize(int value, boolean include) {
        if (contains(value)) {
            return size(left[root]) + (include ? 1 : 0);
        }
        int result = 0;
        int node = root;
        while (node != -1) {
            if (compare(value, this.value[node]) > 0) {
                result += 1 + size(left[node]);
                node = right[node];
            } else {
                node = left[node];
            }
        }
        return result;
    }

    public int tailSetSize(int value, boolean include) {
        return size() - headSetSize(value, !include);
    }

    protected static class Split {
        protected int left = -1;
        protected int right = -1;
    }
}
