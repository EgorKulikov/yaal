package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

import static java.lang.Integer.MAX_VALUE;
import static net.egork.misc.MiscUtils.MOD7;

public class FibonacciRepresentationsSmall {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        NavigableSet<Integer> ids = new TreeSet<>();
        int root = 0;
        for (int i = 0; i < n; i++) {
            PriorityQueue<Integer> toAdd = new PriorityQueue<>(new ReverseComparator<>());
            toAdd.add(a[i]);
            int minRemove = MAX_VALUE;
            int maxRemove = 0;
            IntList reallyAdd = new IntArrayList();
            while (!toAdd.isEmpty()) {
                int current = toAdd.poll();
                minRemove = Math.min(minRemove, current);
                maxRemove = Math.max(maxRemove, current);
                if (ids.contains(current)) {
                    ids.remove(current);
                    toAdd.add(current + 1);
                    if (current > 2) {
                        toAdd.add(current - 2);
                    } else if (current == 2) {
                        toAdd.add(1);
                    }
                } else if (ids.contains(current + 1)) {
                    ids.remove(current + 1);
                    toAdd.add(current + 2);
                } else if (ids.contains(current - 1)) {
                    minRemove = Math.min(minRemove, current - 1);
                    ids.remove(current - 1);
                    toAdd.add(current + 1);
                } else {
                    ids.add(current);
                    reallyAdd.add(current);
                }
            }
            Integer lower = ids.lower(minRemove);
            Integer upper = ids.higher(maxRemove);
            int left;
            if (lower == null) {
                left = 0;
            } else {
                split(root, lower);
                root = split[1];
                left = split[0];
            }
            int right;
            if (upper == null) {
                right = 0;
                if (root != 0) {
                    reclaim(root);
                }
            } else {
                split(root, upper);
                right = split[1];
                if (split[0] != 0) {
                    reclaim(split[0]);
                }
            }
            root = left;
            reallyAdd.sort();
            for (int j : reallyAdd) {
                if (lower != null) {
                    root = join(root, newNode(lower, j));
                }
                lower = j;
            }
            if (upper != null) {
                root = join(root, newNode(lower, upper));
            }
            root = join(root, right);
            out.printLine(value(root, 1, (ids.first() - 1) / 2));
        }
    }

    private void reclaim(int root) {
        if (root != 0) {
            freeNodes.add(root);
            reclaim(left[root]);
            reclaim(right[root]);
        }
    }

    IntList freeNodes = new IntArrayList();

    static Random random = new Random(239);

    static long[] temp = new long[4];
    static int[] split = new int[2];

    static final int SIZE = 1500000;

    int[] f = new int[SIZE];
    long[] key = new long[SIZE];
    long[] hereMatrix = new long[SIZE * 4];
    long[] totalMatrix = new long[SIZE * 4];
    int[] left = new int[SIZE];
    int[] right = new int[SIZE];
    boolean[] dirty = new boolean[SIZE];
    {
        key[0] = Long.MIN_VALUE;
        totalMatrix[0] = 1;
        totalMatrix[3] = 1;
    }

    int nextNode = 1;

    int newNode(int from, int to) {
        int cNode = freeNodes.isEmpty() ? nextNode++ : freeNodes.last();
        if (!freeNodes.isEmpty()) {
            freeNodes.popLast();
        }
        f[cNode] = from;
        key[cNode] = random.nextLong();
        int matrixAt = cNode << 2;
        hereMatrix[matrixAt] = 1;
        hereMatrix[matrixAt + 2] = 1;
        hereMatrix[matrixAt + 1] = (to - from - 1) >> 1;
        hereMatrix[matrixAt + 3] = (to - from) >> 1;
        dirty[cNode] = true;
        left[cNode] = right[cNode] = 0;
        return cNode;
    }

    static void multiply(long[] c, int cShift, long[] a, int aShift, long[] b, int bShift) {
        c[0 + cShift] = (a[0 + aShift] * b[0 + bShift] + a[1 + aShift] * b[2 + bShift]) % MOD7;
        c[1 + cShift] = (a[0 + aShift] * b[1 + bShift] + a[1 + aShift] * b[3 + bShift]) % MOD7;
        c[2 + cShift] = (a[2 + aShift] * b[0 + bShift] + a[3 + aShift] * b[2 + bShift]) % MOD7;
        c[3 + cShift] = (a[2 + aShift] * b[1 + bShift] + a[3 + aShift] * b[3 + bShift]) % MOD7;
    }

    void recalculate2(int node) {
        if (!dirty[node]) {
            return;
        }
        dirty[node] = false;
        recalculate2(left[node]);
        recalculate2(right[node]);
        multiply(temp, 0, totalMatrix, left[node] << 2, hereMatrix, node << 2);
        multiply(totalMatrix, node << 2, temp, 0, totalMatrix, right[node] << 2);
    }

    public void split(int node, int at) {
        if (node == 0) {
            split[0] = split[1] = 0;
            return;
        }
        if (f[node] >= at) {
            split(left[node], at);
            left[node] = split[1];
            dirty[node] = true;
            split[1] = node;
        } else {
            split(right[node], at);
            right[node] = split[0];
            dirty[node] = true;
            split[0] = node;
        }
    }

    public int join(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (key[a] > key[b]) {
            right[a] = join(right[a], b);
            dirty[a] = true;
            return a;
        }
        left[b] = join(a, left[b]);
        dirty[b] = true;
        return b;
    }

    public long value(int node, long a, long b) {
        recalculate2(node);
        int mId = node << 2;
        return (a * (totalMatrix[mId] + totalMatrix[mId + 1]) + b * (totalMatrix[mId + 2] + totalMatrix[mId + 3])) % MOD7;
    }

}
