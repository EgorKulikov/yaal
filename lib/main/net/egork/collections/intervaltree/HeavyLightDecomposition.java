//package net.egork.collections.intervaltree;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author Egor Kulikov (egorku@yandex-team.ru)
// */
//public abstract class HeavyLightDecomposition {
//    private final int[][] graph;
//    private final int[] quantities;
//    private final int[] level;
//    private final int[] heavyChild;
//    private final IntervalTree[] tree;
//    private final int[] indexInTree;
//    private final LongIntervalTree lcmTree;
//    private final long[] order;
//    private final int[] position;
//    private final int[] parent;
//
//    public HeavyLightDecomposition(int[][] graph) {
//        this.graph = graph;
//        quantities = new int[graph.length];
//        level = new int[graph.length];
//        calculateQuantitiesAndLevel(0, -1, 0);
//		order = new long[2 * graph.length - 1];
//		position = new int[graph.length];
//		calculateOrder(0, -1, 0);
//        heavyChild = new int[graph.length];
//        Arrays.fill(heavyChild, -1);
//        calculateHeavyChildren(0, -1);
//        //noinspection unchecked
//        tree = new SimpleIntervalTree[graph.length];
//        indexInTree = new int[graph.length];
//        parent = new int[graph.length];
//        calculateTrees(0, -1);
//        lcmTree = new ArrayBasedIntervalTree(order) {
//            @Override
//            protected long joinValue(long left, long right) {
//                if (left == -1)
//                    return right;
//                if (right == -1)
//                    return left;
//                if (level[((int) left)] < level[((int) right)])
//                    return left;
//                return right;
//            }
//
//            @Override
//            protected long joinDelta(long was, long delta) {
//                return was;
//            }
//
//            @Override
//            protected long accumulate(long value, long delta, int length) {
//                return value;
//            }
//
//            @Override
//            protected long neutralValue() {
//                return -1;
//            }
//
//            @Override
//            protected long neutralDelta() {
//                return 0;
//            }
//        };
//        lcmTree.init();
//    }
//
//    private int calculateOrder(int vertex, int last, int currentPosition) {
//        position[vertex] = currentPosition;
//        order[currentPosition++] = vertex;
//        for (int i : graph[vertex]) {
//            if (i != last) {
//                currentPosition = calculateOrder(i, vertex, currentPosition);
//                order[currentPosition++] = vertex;
//            }
//        }
//        return currentPosition;
//    }
//
//    private void calculateTrees(int vertex, int last) {
//        if (tree[vertex] == null) {
//            List<Integer> list = new ArrayList<Integer>();
//            int current = vertex;
//            while (current != -1) {
//                list.add(current);
//                current = heavyChild[current];
//            }
//            SimpleIntervalTree<V, D> currentTree = new SimpleIntervalTree<V, D>(list.size()) {
//                @Override
//                protected V joinValue(V left, V right) {
//                    return HeavyLightDecomposition.this.joinValue(left, right);
//                }
//
//                @Override
//                protected D joinDelta(D was, D delta) {
//                    return HeavyLightDecomposition.this.joinDelta(was, delta);
//                }
//
//                @Override
//                protected V accumulate(V value, D delta, int length) {
//                    return HeavyLightDecomposition.this.accumulate(value, delta, length);
//                }
//
//                @Override
//                protected V neutralValue() {
//                    return HeavyLightDecomposition.this.neutralValue();
//                }
//
//                @Override
//                protected D neutralDelta() {
//                    return HeavyLightDecomposition.this.neutralDelta();
//                }
//            };
//            currentTree.init();
//            for (int i = 0; i < list.size(); i++) {
//                tree[list.get(i)] = currentTree;
//                indexInTree[list.get(i)] = i;
//                parent[list.get(i)] = last;
//            }
//        }
//        for (int i : graph[vertex]) {
//            if (i != last)
//                calculateTrees(i, vertex);
//        }
//    }
//
//    private void calculateHeavyChildren(int vertex, int last) {
//        for (int i : graph[vertex]) {
//            if (i != last) {
//                calculateHeavyChildren(i, vertex);
//                if (quantities[i] * 2 >= quantities[vertex])
//                    heavyChild[vertex] = i;
//            }
//        }
//    }
//
//    private int calculateQuantitiesAndLevel(int vertex, int last, int currentLevel) {
//        quantities[vertex] = 1;
//        level[vertex] = currentLevel;
//        for (int i : graph[vertex]) {
//            if (i != last)
//                quantities[vertex] += calculateQuantitiesAndLevel(i, vertex, currentLevel + 1);
//        }
//        return quantities[vertex];
//    }
//
//    public void update(int from, int to, D delta) {
//        int lcm = (int) lcmTree.query(Math.min(position[from], position[to]), Math.max(position[from], position[to]));
//        updateImpl(from, lcm, delta);
//        updateImpl(to, lcm, delta);
//        tree[lcm].update(indexInTree[lcm], indexInTree[lcm], delta);
//    }
//
//    private void updateImpl(int from, int to, D delta) {
//        while (tree[from] != tree[to]) {
//            tree[from].update(0, indexInTree[from], delta);
//            from = parent[from];
//        }
//        tree[from].update(indexInTree[to] + 1, indexInTree[from], delta);
//    }
//
//    public V query(int from, int to) {
//        int lcm = (int) lcmTree.query(Math.min(position[from], position[to]), Math.max(position[from], position[to]));
//        V result = joinValue(queryImpl(from, lcm), queryImpl(to, lcm));
//        return joinValue(result, tree[lcm].query(indexInTree[lcm], indexInTree[lcm]));
//    }
//
//    private V queryImpl(int from, int to) {
//        V result = neutralValue();
//        while (tree[from] != tree[to]) {
//            result = joinValue(result, tree[from].query(0, indexInTree[from]));
//            from = parent[from];
//        }
//        result = joinValue(result, tree[from].query(indexInTree[to] + 1, indexInTree[from]));
//        return result;
//    }
//
//    protected abstract V joinValue(V left, V right);
//    protected abstract D joinDelta(D was, D delta);
//    protected abstract V accumulate(V value, D delta, int length);
//    protected abstract V neutralValue();
//    protected abstract D neutralDelta();
//}
