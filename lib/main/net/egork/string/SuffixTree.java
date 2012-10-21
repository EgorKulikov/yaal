package net.egork.string;

import java.util.Stack;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class SuffixTree {
    protected final String s;
    protected final StringHash hash;
    public final Node root;
    protected final int length;
    protected final int minCode;
    protected final int alphabetSize;

    public SuffixTree(String s, int minCode, int maxCode) {
        this.s = s;
        length = s.length();
        hash = new SimpleStringHash(s);
        this.minCode = minCode;
        alphabetSize = maxCode - minCode + 1;
        int[] order = StringUtils.suffixArray(s);
        root = createNode(0, 0, 0);
        Stack<Node> stack = new Stack<Node>();
        stack.add(root);
        for (int i : order)
            join(i, stack);
    }

    private void join(int start, Stack<Node> stack) {
        while (true) {
            Node head = stack.peek();
            if (head.to - head.start <= length - start && hash.hash(head.start, head.to) == hash.hash(start, start + head.to - head.start))
                break;
            stack.pop();
        }
        Node head = stack.peek();
        int index = start + head.to - head.start;
        int letterIndex = s.charAt(index) - minCode;
        if (head.children[letterIndex] == null) {
            head.children[letterIndex] = new Node(start, index, length);
            stack.add(head.children[letterIndex]);
            return;
        }
        Node toChange = head.children[letterIndex];
        int left = 1;
        int right = Math.min(length - index, toChange.to - toChange.from);
        while (left < right) {
            int middle = (left + right + 1) >> 1;
            if (hash.hash(index, index + middle) == hash.hash(toChange.from, toChange.from + middle))
                left = middle;
            else
                right = middle - 1;
        }
        head.children[letterIndex] = new Node(start, index, index + left);
        Node next = head.children[letterIndex];
        next.children[s.charAt(toChange.from + left) - minCode] =
                new Node(toChange.start, toChange.from + left, toChange.to, toChange.children);
        stack.add(next);
        if (index + left != length) {
            next = next.children[s.charAt(index + left) - minCode] = new Node(start, index + left, length);
            stack.add(next);
        }
    }

    protected Node createNode(int start, int from, int to) {
        return new Node(start, from, to);
    }

    public class Node {
        public final Node[] children;
        public final int start;
        public final int from;
        public final int to;

        public Node(int start, int from, int to) {
            this(start, from, to, new Node[alphabetSize]);
        }

        public Node(int start, int from, int to, Node[] children) {
            this.start = start;
            this.from = from;
            this.to = to;
            this.children = children;
        }
    }
}
