package on2014_03.on2014_03_23_OpenCup_Kazan_GP.TaskJ;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] type = new int[count];
		int[] at = new int[count];
		for (int i = 0; i < count; i++) {
			String command = in.readString();
			if (command.charAt(0) == 'A')
				type[i] = 0;
			else if (command.charAt(0) == 'F')
				type[i] = 1;
			else
				type[i] = 2;
			at[i] = in.readInt();
		}
		int[] sorted = at.clone();
		Arrays.sort(sorted);
		sorted = ArrayUtils.unique(sorted);
		for (int i = 0; i < count; i++)
			at[i] = Arrays.binarySearch(sorted, at[i]);
		int size = sorted.length;
		Node[] nodes = new Node[size];
		IntervalTree tree = new SumIntervalTree(size);
		NavigableSet<Node> set = new TreeSet<>();
		for (int i = 0; i < count; i++) {
			if (type[i] == 1) {
				if (nodes[at[i]] == null) {
					Node left = set.lower(new Node(at[i]));
					Node right = set.higher(new Node(at[i]));
					int result = 1;
					if (left != null)
						result = (int) Math.max(result, tree.query(left.id, left.id));
					if (right != null)
						result = (int) Math.max(result, tree.query(right.id, right.id));
					out.printLine("FALSE", result);
				} else
					out.printLine("TRUE", tree.query(at[i], at[i]));
			} else if (type[i] == 0) {
				if (nodes[at[i]] != null)
					out.printLine("FALSE", tree.query(at[i], at[i]));
				else {
					Node node = nodes[at[i]] = new Node(at[i]);
					Node left = set.lower(node);
					Node right = set.higher(node);
					Node parent = null;
					if (left != null)
						parent = left;
					if (right != null && (parent == null || tree.query(parent.id, parent.id) < tree.query(right.id, right.id)))
						parent = right;
					node.parentLeft = left == null ? Node.LEFT : left;
					node.parentRight = right == null ? Node.RIGHT : right;
					if (parent != null) {
						if (parent.id < at[i])
							parent.right = node;
						else
							parent.left = node;
						tree.update(at[i], at[i], tree.query(parent.id, parent.id) + 1 - tree.query(at[i], at[i]));
					} else
						tree.update(at[i], at[i], 1 - tree.query(at[i], at[i]));
					out.printLine("TRUE", tree.query(at[i], at[i]));
					set.add(node);
				}
			} else {
				if (nodes[at[i]] == null) {
					Node left = set.lower(new Node(at[i]));
					Node right = set.higher(new Node(at[i]));
					int result = 1;
					if (left != null)
						result = (int) Math.max(result, tree.query(left.id, left.id));
					if (right != null)
						result = (int) Math.max(result, tree.query(right.id, right.id));
					out.printLine("FALSE", result);
				} else {
					long level = tree.query(at[i], at[i]);
					Node node = nodes[at[i]];
					set.remove(node);
					node.parentLeft = updateLeft(node.parentLeft);
					node.parentRight = updateRight(node.parentRight);
					Node parent;
					if (node.parentLeft.right == node)
						parent = node.parentLeft;
					else
						parent = node.parentRight;
					if (node.left == null && node.right == null) {
						nodes[at[i]] = null;
						out.printLine("TRUE", level);
						if (parent.id > node.id)
							parent.left = null;
						else
							parent.right = null;
						continue;
					}
					if (node.left == null) {
						tree.update(node.parentLeft.id + 1, node.parentRight.id - 1, -1);
						node.right.parentLeft = node.parentLeft;
						node.right.parentRight = node.parentRight;
						node.replacedByLeft = node.parentLeft;
						if (parent.id > node.id)
							parent.left = node.right;
						else
							parent.right = node.right;
					} else if (node.right == null) {
						tree.update(node.parentLeft.id + 1, node.parentRight.id - 1, -1);
						node.left.parentLeft = node.parentLeft;
						node.left.parentRight = node.parentRight;
						node.replacedByRight = node.parentRight;
						if (parent.id > node.id)
							parent.left = node.left;
						else
							parent.right = node.left;
					} else {
						Node replacement = set.higher(node);
						replacement.parentRight = updateRight(replacement.parentRight);
						if (node.right == replacement) {
							tree.update(replacement.id, replacement.parentRight.id - 1, -1);
							level++;
							replacement.left = node.left;
							replacement.parentLeft = node.parentLeft;
							if (parent.right == node)
								parent.right = replacement;
							else
								parent.left = replacement;
						} else {
							tree.update(replacement.id + 1, replacement.parentRight.id - 1, -1);
							long replacementLevel = tree.query(replacement.id, replacement.id);
							level = Math.max(level, replacementLevel);
							tree.update(replacement.id, replacement.id, tree.query(node.id, node.id) - replacementLevel);
							replacement.parentRight.left = replacement.right;
							replacement.left = node.left;
							replacement.right = node.right;
							replacement.parentLeft = node.parentLeft;
							replacement.parentRight = node.parentRight;
							if (node.parentLeft.right == node)
								node.parentLeft.right = replacement;
							else
								node.parentRight.left = replacement;
						}
						node.replacedByLeft = replacement;
						node.replacedByRight = replacement;
					}
					out.printLine("TRUE", level);
					nodes[at[i]] = null;
				}
			}
		}
    }

	private Node updateLeft(Node node) {
		if (node.replacedByLeft == null)
			return node;
		return node.replacedByLeft = updateLeft(node.replacedByLeft);
	}

	private Node updateRight(Node node) {
		if (node.replacedByRight == null)
			return node;
		return node.replacedByRight = updateLeft(node.replacedByRight);
	}

	static class Node implements Comparable<Node> {
		static final Node LEFT = new Node(-1);
		static final Node RIGHT = new Node(1000000);

		int id;
		Node left;
		Node right;
		Node parentLeft;
		Node parentRight;
		Node replacedByRight;
		Node replacedByLeft;

		Node(int id) {
			this.id = id;
			parentLeft = LEFT;
			parentRight = RIGHT;
		}

		@Override
		public int compareTo(Node o) {
			return id - o.id;
		}
	}
}
