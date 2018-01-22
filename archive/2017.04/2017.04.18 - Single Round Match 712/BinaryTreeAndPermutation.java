package net.egork;

import net.egork.collections.intervaltree.LCA;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

import static net.egork.misc.ArrayUtils.createArray;

public class BinaryTreeAndPermutation {
    public int[] findPermutation(int[] lef, int[] rig, int[] a, int[] b, int[] c) {
        int n = lef.length;
        int m = a.length;
        int[] parent = createArray(n, -1);
        Graph tree = new BidirectionalGraph(n);
        for (int i = 0; i < n; i++) {
            if (lef[i] != -1) {
                tree.addSimpleEdge(i, lef[i]);
                tree.addSimpleEdge(i, rig[i]);
                parent[lef[i]] = i;
                parent[rig[i]] = i;
            }
        }
        LCA lca = new LCA(tree);
        int[] answer = createArray(n, -1);
        for (int i = n - 1; i >= 0; i--) {
            boolean freeRoot = true;
            for (int j = 0; j < n; j++) {
                if (answer[j] == i) {
                    freeRoot = false;
                }
            }
            IntList left = new IntArrayList();
            IntList right = new IntArrayList();
            add(lef[i], left, lef, rig, answer);
            add(rig[i], right, lef, rig, answer);
            IntList vertices = new IntArrayList();
            Graph graph = new BidirectionalGraph(n);
            for (int j = 0; j < m; j++) {
                if (c[j] == i) {
                    if (answer[a[j]] != -1) {
                        if (answer[b[j]] != -1) {
                            if (lca.getLCA(answer[a[j]], answer[b[j]]) != c[j]) {
                                return new int[0];
                            }
                        } else {
                            int current = answer[a[j]];
                            int last = -1;
                            while (current != c[j] && current != -1) {
                                last = current;
                                current = parent[current];
                            }
                            if (current == -1) {
                                return new int[0];
                            }
                            if (last == -1) {
                                vertices.add(b[j]);
                            } else {
                                if (last == rig[c[j]]) {
                                    if (left.isEmpty()) {
                                        return new int[0];
                                    }
                                    answer[b[j]] = left.last();
                                    left.popLast();
                                } else {
                                    if (right.isEmpty()) {
                                        return new int[0];
                                    }
                                    answer[b[j]] = right.last();
                                    right.popLast();
                                }
                            }
                        }
                    } else if (answer[b[j]] != -1) {
                        int current = answer[b[j]];
                        int last = -1;
                        while (current != c[j] && current != -1) {
                            last = current;
                            current = parent[current];
                        }
                        if (current == -1) {
                            return new int[0];
                        }
                        if (last == -1) {
                            vertices.add(b[j]);
                        } else {
                            if (last == rig[c[j]]) {
                                if (left.isEmpty()) {
                                    return new int[0];
                                }
                                answer[a[j]] = left.last();
                                left.popLast();
                            } else {
                                if (right.isEmpty()) {
                                    return new int[0];
                                }
                                answer[a[j]] = right.last();
                                right.popLast();
                            }
                        }
                    } else {
                        if (a[j] == b[j]) {
                            if (freeRoot) {
                                answer[a[j]] = c[j];
                                freeRoot = false;
                            } else {
                                return new int[0];
                            }
                        } else {
                            vertices.add(a[j]);
                            vertices.add(b[j]);
                            graph.addSimpleEdge(a[j], b[j]);
                        }
                    }
                }
            }
            vertices.sort();
            vertices = vertices.unique();
//            List<Pair<IntList, IntList>>

        }
        return null;
    }

    private void add(int vertex, IntList list, int[] lef, int[] rig, int[] answer) {
        if (vertex == -1) {
            return;
        }
        boolean good = true;
        for (int i : answer) {
            if (i == vertex) {
                good = false;
                break;
            }
        }
        if (good) {
            list.add(vertex);
        }
        add(lef[vertex], list, lef, rig, answer);
        add(rig[vertex], list, lef, rig, answer);
    }
}
