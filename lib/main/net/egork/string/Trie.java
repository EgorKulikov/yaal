package net.egork.string;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Trie {
    private Node root = new Node();

    public void add(CharSequence word) {
        Node current = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char letter = word.charAt(i);
            if (current.links[letter] == null) {
                current.links[letter] = new Node();
            }
            current = current.links[letter];
        }
        current.leaf = true;
    }

    public Visitor getVisitor() {
        return new Visitor();
    }

    public class Visitor {
        private Node node = root;

        private Visitor() {
        }

        public boolean accept(char letter) {
            if (node == null) {
                return false;
            }
            node = node.links[letter];
            if (node == null) {
                return false;
            }
            if (node.leaf) {
                node = root;
            }
            return true;
        }

        public boolean isAtRoot() {
            return node == root;
        }
    }

    private static class Node {
        private Node[] links = new Node[128];
        private boolean leaf = false;
    }
}
