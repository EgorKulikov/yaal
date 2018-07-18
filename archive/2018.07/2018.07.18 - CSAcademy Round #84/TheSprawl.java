package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Random;

import static net.egork.TheSprawl.Node.join;
import static net.egork.TheSprawl.Node.nullNode;
import static net.egork.misc.ArrayUtils.*;

public class TheSprawl {
    long answer;
    long curWeight;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        in.readIntArrays(x, y);
        int[] order = createOrder(n);
        int[] from = new int[10 * n];
        int[] to = new int[10 * n];
        int[] weight = new int[10 * n];
        int size = 0;
        for (int u = 0; u < 2; u++) {
            for (int v = 0; v < 2; v++) {
                for (int t = 0; t < 2; t++) {
                    sort(order, (a, b) -> Integer.compare(y[a] - x[a], y[b] - x[b]) == 0 ? x[b] - x[a] : Integer
                            .compare(y[a] - x[a], y[b] - x[b]));
                    Node root = nullNode;
                    for (int i : order) {
                        Node toAdd = new Node(x[i] + y[i], y[i], i);
                        Pair<Node, Node> split = root.split(toAdd);
                        if (split.second != nullNode) {
                            from[size] = i;
                            to[size] = split.second.minId;
                            weight[size] = y[to[size]] - y[i] + x[to[size]] - x[i];
                            size++;
                        }
                        root = join(split.first, split.second);
                        root = root.add(toAdd);
                    }
                    for (int i = 0; i < n; i++) {
                        x[i] = -x[i];
                    }
                }
                for (int i = 0; i < n; i++) {
                    y[i] = -y[i];
                }
            }
            for (int i = 0; i < n; i++) {
                int temp = x[i];
                x[i] = y[i];
                y[i] = temp;
            }
        }
        sort(order, (a, b) -> x[a] != x[b] ? x[a] - x[b] : y[a] - y[b]);
        for (int i = 1; i < n; i++) {
            if (x[order[i]] == x[order[i - 1]]) {
                from[size] = order[i];
                to[size] = order[i - 1];
                weight[size++] = y[order[i]] - y[order[i - 1]];
            }
        }
        sort(order, (a, b) -> y[a] != y[b] ? y[a] - y[b] : x[a] - x[b]);
        for (int i = 1; i < n; i++) {
            if (y[order[i]] == y[order[i - 1]]) {
                from[size] = order[i];
                to[size] = order[i - 1];
                weight[size++] = x[order[i]] - x[order[i - 1]];
            }
        }
        int[] sizes = createArray(n, 1);
        order = createOrder(size);
        sort(order, (a, b) -> Integer.compare(weight[a], weight[b]));
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        answer = 0;
        setSystem.setListener(new IndependentSetSystem.Listener() {
            @Override
            public void joined(int joinedRoot, int root) {
                answer += (long)sizes[root] * sizes[joinedRoot] * curWeight;
                sizes[root] += sizes[joinedRoot];
            }
        });
        for (int i : order) {
            curWeight = weight[i] / 2;
            setSystem.join(from[i], to[i]);
        }
        out.printLine(answer);
    }

    static class Node {
        static Random random = new Random(43);
        static Node nullNode = new NullNode();
        static Pair<Node, Node> nullPair = Pair.makePair(nullNode, nullNode);

        int value;
        int min;
        int sorted;
        int id;
        int minId;
        long key = random.nextLong();
        Node left = nullNode;
        Node right = nullNode;

        public Node(int value, int sorted, int id) {
            this.value = value;
            this.sorted = sorted;
            this.id = id;
            minId = id;
            min = value;
        }

        public Node add(Node toAdd) {
            if (toAdd.key < key) {
                if (toAdd.sorted < sorted || toAdd.sorted == sorted && toAdd.id < id) {
                    left = left.add(toAdd);
                } else {
                    right = right.add(toAdd);
                }
                update();
                return this;
            } else {
                Pair<Node, Node> split = split(toAdd);
                toAdd.left = split.first;
                toAdd.right = split.second;
                toAdd.update();
                return toAdd;
            }
        }

        public Pair<Node, Node> split(Node node) {
            if (sorted < node.sorted || sorted == node.sorted && id < node.id) {
                Pair<Node, Node> split = right.split(node);
                right = split.first;
                update();
                return Pair.makePair(this, split.second);
            } else {
                Pair<Node, Node> split = left.split(node);
                left = split.second;
                update();
                return Pair.makePair(split.first, this);
            }
        }

        private void update() {
            min = value;
            minId = id;
            if (left.min < min) {
                min = left.min;
                minId = left.minId;
            }
            if (right.min < min) {
                min = right.min;
                minId = right.minId;
            }
        }

        public static Node join(Node left, Node right) {
            if (left == nullNode) {
                return right;
            }
            if (right == nullNode) {
                return left;
            }
            if (left.key > right.key) {
                left.right = join(left.right, right);
                left.update();
                return left;
            }
            right.left = join(left, right.left);
            right.update();
            return right;
        }
    }

    static class NullNode extends Node {
        public NullNode() {
            super(Integer.MAX_VALUE, 0, -1);
            key = Long.MIN_VALUE;
        }

        @Override
        public Node add(Node toAdd) {
            return toAdd;
        }

        @Override
        public Pair<Node, Node> split(Node by) {
            return nullPair;
        }
    }
}
