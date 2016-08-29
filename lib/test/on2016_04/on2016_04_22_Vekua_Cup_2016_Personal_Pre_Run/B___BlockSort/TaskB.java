package on2016_04.on2016_04_22_Vekua_Cup_2016_Personal_Pre_Run.B___BlockSort;



import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

import static net.egork.io.IOUtils.readIntArray;

public class TaskB {
    static Random random = new Random(239);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        Node root = null;
        for (int i : a) {
            Node current = new Node(i);
            root = merge(root, current);
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int position = root.minAt();
            Pair<Node, Node> split = root.split(position);
            answer[i] = i + position + 1;
            revert(split.first);
            root = merge(split.first, split.second);
        }
        out.printLine(answer);
    }

    static class Node {
        int value;
        long priority = random.nextLong();
        int min;
        boolean revert;
        Node left;
        Node right;
        int size;

        public Node(int value) {
            this.value = value;
            this.min = value;
            this.size = 1;
        }

        public void normalize() {
            if (revert) {
                revert = false;
                Node temp = left;
                left = right;
                right = temp;
                TaskB.revert(left);
                TaskB.revert(right);
            }
        }

        private void revert() {
            revert ^= true;
        }

        public void update() {
            min = value;
            size = 1;
            min = Math.min(min, min(left));
            size += size(left);
            min = Math.min(min, min(right));
            size += size(right);
        }

        public Pair<Node, Node> split(int at) {
            normalize();
            if (size(left) == at) {
                return Pair.makePair(left, right);
            }
            if (size(left) > at) {
                Pair<Node, Node> split = left.split(at);
                left = split.second;
                update();
                return Pair.makePair(split.first, this);
            } else {
                Pair<Node, Node> split = right.split(at - size(left) - 1);
                right = split.first;
                update();
                return Pair.makePair(this, split.second);
            }
        }

        int minAt() {
            normalize();
            if (min(left) == min) {
                return left.minAt();
            }
            if (value == min) {
                return size(left);
            }
            return 1 + size(left) + right.minAt();
        }
    }

    static void revert(Node node) {
        if (node != null) {
            node.revert();
        }
    }

    static int min(Node node) {
        if (node != null) {
            return node.min;
        }
        return Integer.MAX_VALUE;
    }

    static int size(Node node) {
        if (node != null) {
            return node.size;
        }
        return 0;
    }

    static Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.priority > right.priority) {
            left.normalize();
            left.right = merge(left.right, right);
            left.update();
            return left;
        } else {
            right.normalize();
            right.left = merge(left, right.left);
            right.update();
            return right;
        }
    }
}
