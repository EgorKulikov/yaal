package net.egork.collections.set;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class PersistentTreapSet<K> extends TreapSet<K> implements PersistentSet<K> {
    private Map<Object, Node> states = new HashMap<Object, Node>();

    public PersistentTreapSet() {
        super();
    }

    public PersistentTreapSet(Comparator<? super K> comparator) {
        super(comparator);
    }

    public PersistentTreapSet(Iterable<? extends K> collection) {
        super(collection);
    }

    public PersistentTreapSet(Iterable<? extends K> collection, Comparator<? super K> comparator) {
        super(collection, comparator);
    }

    public PersistentTreapSet(Node root, Comparator<? super K> comparator, Node nullNode) {
        super(null, null, false, false, comparator, root, nullNode);
    }

    public void markState(Object marker) {
        //noinspection unchecked
        states.put(marker, root);
    }

    public PersistentTreapSet<K> getState(Object marker) {
        return new PersistentTreapSet<K>(states.get(marker), comparator, nullNode);
    }

    @Override
    protected Node createNode(K k) {
        return new PersistentNode(k, rnd.nextLong());
    }

    protected class PersistentNode extends Node {
        protected PersistentNode(K key, long priority) {
            super(key, priority);
        }

        @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneDoesntDeclareCloneNotSupportedException"})
        @Override
        protected Node clone() {
            Node clone = new PersistentNode(key, priority);
            clone.left = left;
            clone.right = right;
            clone.size = size;
            return clone;
        }

        @SuppressWarnings({"unchecked"})
        protected Object[] split(K key) {
            Node clone = clone();
            if (compare(key, this.key) < 0) {
                Object[] result = left.split(key);
                clone.left = (Node) result[1];
                result[1] = clone;
                clone.updateSize();
                return result;
            }
            Object[] result = right.split(key);
            clone.right = (Node) result[0];
            result[0] = clone;
            clone.updateSize();
            return result;
        }

        @SuppressWarnings({"unchecked"})
        protected Node insert(Node node) {
            if (node.priority > priority) {
                Object[] result = split(node.key);
                node.left = (Node) result[0];
                node.right = (Node) result[1];
                node.updateSize();
                return node;
            }
            Node clone = clone();
            if (compare(node.key, this.key) < 0) {
                clone.left = left.insert(node);
                clone.updateSize();
                return clone;
            }
            clone.right = right.insert(node);
            clone.updateSize();
            return clone;
        }

        protected Node merge(Node left, Node right) {
            if (left == nullNode) {
                return right;
            }
            if (right == nullNode) {
                return left;
            }
            if (left.priority > right.priority) {
                //noinspection unchecked
                left = ((PersistentNode) left).clone();
                left.right = left.right.merge(left.right, right);
                left.updateSize();
                return left;
            }
            //noinspection unchecked
            right = ((PersistentNode) right).clone();
            right.left = right.left.merge(left, right.left);
            right.updateSize();
            return right;
        }

        protected Node erase(K key) {
            int value = compare(key, this.key);
            if (value == 0) {
                return merge(left, right);
            }
            Node clone = clone();
            if (value < 0) {
                clone.left = left.erase(key);
                clone.updateSize();
                return clone;
            }
            clone.right = right.erase(key);
            clone.updateSize();
            return clone;
        }
    }
}
