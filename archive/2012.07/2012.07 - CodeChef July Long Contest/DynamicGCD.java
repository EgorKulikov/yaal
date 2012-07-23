package net.egork;

import net.egork.collections.intervaltree.ArrayBasedLongIntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicGCD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        IOUtils.readIntArrays(in, from, to);
        int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
        HeavyLightDecomposition decomposition = new HeavyLightDecomposition(graph);
        int[] values = IOUtils.readIntArray(in, count);
        for (int i = 0; i < count; i++)
            decomposition.update(i, i, values[i]);
        int queryCount = in.readInt();
        for (int i = 0; i < queryCount; i++) {
            char type = in.readCharacter();
            if (type == 'C') {
                int currentFrom = in.readInt();
                int currentTo = in.readInt();
                int delta = in.readInt();
                decomposition.update(currentFrom, currentTo, delta);
            } else {
                int currentFrom = in.readInt();
                int currentTo = in.readInt();
                int result = decomposition.query(currentFrom, currentTo);
                out.printLine(result);
            }
        }
	}
}

class HeavyLightDecomposition {
    private final int[][] graph;
    private final int[] quantities;
    private final int[] level;
    private final int[] heavyChild;
    private final IntervalTree[] tree;
    private final int[] indexInTree;
    private final LongIntervalTree lcmTree;
    private final long[] order;
    private final int[] position;
    private final int[] parent;
    final int[] directValue;

    public HeavyLightDecomposition(int[][] graph) {
        this.graph = graph;
        quantities = new int[graph.length];
        level = new int[graph.length];
        int[] queue = new int[graph.length];
        boolean[] processed = new boolean[graph.length];
        processed[0] = true;
        int size = 1;
        parent = new int[graph.length];
        parent[0] = -1;
        for (int i = 0; i < size; i++) {
            for (int j : graph[queue[i]]) {
                if (!processed[j]) {
                    processed[j] = true;
                    queue[size++] = j;
                    parent[j] = queue[i];
                }
            }
        }
        calculateQuantitiesAndLevel(queue);
        heavyChild = new int[graph.length];
        Arrays.fill(heavyChild, -1);
        calculateHeavyChildren(queue);
        order = new long[2 * graph.length - 1];
        position = new int[graph.length];
        calculateOrder(queue);
        tree = new IntervalTree[graph.length];
        indexInTree = new int[graph.length];
        directValue = new int[graph.length];
        calculateTrees(queue);
        lcmTree = new ArrayBasedLongIntervalTree(order) {
            @Override
            protected long joinValue(long left, long right) {
                if (left == -1)
                    return right;
                if (right == -1)
                    return left;
                if (level[((int) left)] < level[((int) right)])
                    return left;
                return right;
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return was;
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                return value;
            }

            @Override
            protected long neutralValue() {
                return -1;
            }

            @Override
            protected long neutralDelta() {
                return 0;
            }
        };
        lcmTree.init();
    }

    private void calculateOrder(int[] queue) {
        int[] next = new int[queue.length];
        int current = 0;
        int curPosition = 0;
        while (current != -1) {
            if (position[current] == 0)
                position[current] = curPosition;
            order[curPosition++] = current;
            if (next[current] < graph[current].length && graph[current][next[current]] == parent[current])
                next[current]++;
            if (next[current] == graph[current].length)
                current = parent[current];
            else
                current = graph[current][next[current]++];
        }
    }

    private void calculateTrees(int[] queue) {
        for (int vertex : queue) {
            if (tree[vertex] == null) {
                List<Integer> list = new ArrayList<Integer>();
                int current = vertex;
                while (current != -1) {
                    list.add(current);
                    current = heavyChild[current];
                }
                if (list.size() > 4) {
                    IntervalTree currentTree = new IntervalTree(list.size());
                    for (int i = 0; i < list.size(); i++) {
                        tree[list.get(i)] = currentTree;
                        indexInTree[list.get(i)] = i;
                        parent[list.get(i)] = parent[vertex];
                    }
                }
            }
        }
    }

    private void calculateHeavyChildren(int[] queue) {
        for (int i : queue) {
            for (int j : graph[i]) {
                if (j != parent[i]) {
                    if (quantities[j] * 2 >= quantities[i])
                        heavyChild[i] = j;
                }
            }
        }
    }

    private void calculateQuantitiesAndLevel(int[] queue) {
        for (int i : queue) {
            for (int j : graph[i]) {
                if (j != parent[i])
                    level[j] = level[i] + 1;
            }
        }
        for (int i = queue.length - 1; i >= 0; i--) {
            quantities[i] = 1;
            for (int j : graph[queue[i]]) {
                if (j != parent[queue[i]])
                    quantities[i] += quantities[j];
            }
        }
    }

    public void update(int from, int to, int delta) {
        int lcm = (int) lcmTree.query(Math.min(position[from], position[to]), Math.max(position[from], position[to]));
        updateImpl(from, lcm, delta, true);
        updateImpl(to, lcm, delta, false);
//        tree[lcm].update(indexInTree[lcm], indexInTree[lcm], delta);
    }

    private void updateImpl(int from, int to, int delta, boolean includeTo) {
        while (from != to && (tree[from] == null || tree[from] != tree[to])) {
            if (tree[from] == null)
                directValue[from] += delta;
            else
                tree[from].update(0, indexInTree[from], delta);
            from = parent[from];
        }
        if (tree[from] == null) {
            if (includeTo)
                directValue[from] += delta;
        } else {
            tree[from].update(indexInTree[to] + (includeTo ? 0 : 1), indexInTree[from], delta);
        }
    }

    public int query(int from, int to) {
        int lcm = (int) lcmTree.query(Math.min(position[from], position[to]), Math.max(position[from], position[to]));
        return IntegerUtils.gcd(queryImpl(from, lcm, false), queryImpl(to, lcm, true));
    }

    private int queryImpl(int from, int to, boolean includeTo) {
        int result = 0;
        while (from != to && (tree[from] == null || tree[from] != tree[to])) {
            if (tree[from] == null)
                result = IntegerUtils.gcd(result, directValue[from]);
            else
                result = IntegerUtils.gcd(result, tree[from].query(0, indexInTree[from]));
            from = parent[from];
        }
        if (tree[from] == null) {
            if (includeTo)
                result = IntegerUtils.gcd(result, directValue[from]);
        } else {
            result = IntegerUtils.gcd(result, tree[from].query(indexInTree[to] + (includeTo ? 0 : 1), indexInTree[from]));
        }
        return result;
    }
}

class IntervalTree {
    protected int size;
    protected int[] example;
    protected int[] gcd;
    protected int[] delta;

    protected IntervalTree(int size) {
        this.size = size;
        int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
        example = new int[nodeCount];
        gcd = new int[nodeCount];
        delta = new int[nodeCount];
    }

    protected int initValue(int index) {
        return 0;
    }

    public void update(int from, int to, int delta) {
        update(0, 0, size - 1, from, to, delta);
    }

    private void update(int root, int left, int right, int from, int to, int delta) {
        if (left > to || right < from)
            return;
        if (left >= from && right <= to) {
            this.delta[root] += delta;
            example[root] += delta;
            return;
        }
        this.delta[2 * root + 1] += this.delta[root];
        this.delta[2 * root + 2] += this.delta[root];
        int middle = (left + right) >> 1;
        example[2 * root + 1] += this.delta[root];
        example[2 * root + 2] += this.delta[root];
        this.delta[root] = 0;
        update(2 * root + 1, left, middle, from, to, delta);
        update(2 * root + 2, middle + 1, right, from, to, delta);
        example[root] = example[2 * root + 1];
        gcd[root] = IntegerUtils.gcd(example[2 * root + 1] - example[2 * root + 2], IntegerUtils.gcd(gcd[2 * root + 1], gcd[2 * root + 2]));
    }

    public int query(int from, int to) {
        return query(0, 0, size - 1, from, to);
    }

    private int query(int root, int left, int right, int from, int to) {
        if (left > to || right < from)
            return 0;
        if (left >= from && right <= to)
            return IntegerUtils.gcd(gcd[root], example[root]);
        this.delta[2 * root + 1] += this.delta[root];
        this.delta[2 * root + 2] += this.delta[root];
        int middle = (left + right) >> 1;
        example[2 * root + 1] += this.delta[root];
        example[2 * root + 2] += this.delta[root];
        this.delta[root] = 0;
        return IntegerUtils.gcd(query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
    }
}
