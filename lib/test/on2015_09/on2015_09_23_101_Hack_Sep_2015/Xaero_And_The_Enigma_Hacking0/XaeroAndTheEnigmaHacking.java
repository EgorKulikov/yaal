package on2015_09.on2015_09_23_101_Hack_Sep_2015.Xaero_And_The_Enigma_Hacking0;





import net.egork.collections.Pair;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class XaeroAndTheEnigmaHacking {
	static final Random R = new Random(239);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		long k = in.readLong();
		char[] s = IOUtils.readCharArray(in, n);
		int[] l = new int[m];
		int[] r = new int[m];
		IOUtils.readIntArrays(in, l, r);
		MiscUtils.decreaseByOne(l, r);
		Node root = new Node(0);
		for (int i = 1; i < n; i++) {
			root = merge(root, new Node(i));
		}
		for (int i = 0; i < m; i++) {
			Pair<Node, Node> left = split(root, l[i]);
			Pair<Node, Node> right = split(left.second, r[i] - l[i] + 1);
			root = right.first;
			root.reversed = !root.reversed;
			root = merge(left.first, root);
			root = merge(root, right.second);
		}
		int[] order = new int[n];
		if (traverse(root, 0, order) != n) {
			throw new RuntimeException();
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
		for (int i = 0; i < n; i++) {
			setSystem.join(i, order[i]);
		}
		char[] value = new char[n];
		for (int i = 0; i < n; i++) {
			if (s[i] != '?') {
				int parent = setSystem.get(i);
				if (value[parent] != 0 && value[parent] != s[i]) {
					out.printLine("Bad Luck Allen");
					return;
				}
				value[parent] = s[i];
			}
		}
		Arrays.fill(order, -1);
		int current = 0;
		for (int i = 0; i < n; i++) {
			if (order[setSystem.get(i)] == -1 && value[setSystem.get(i)] == 0) {
				order[setSystem.get(i)] = current++;
			}
		}
		k--;
		int[] result = new int[current];
		for (int i = current - 1; i >= 0; i--) {
			result[i] = (int) (k % 26);
			k /= 26;
		}
		if (k != 0) {
			out.printLine("Bad Luck Allen");
			return;
		}
		for (int i = 0; i < n; i++) {
			int parent = setSystem.get(i);
			if (value[parent] != 0) {
				s[i] = value[parent];
			} else {
				s[i] = (char) ('a' + result[order[parent]]);
			}
		}
		out.printLine(s);
	}

	private int traverse(Node root, int at, int[] order) {
		if (root == null) {
			return at;
		}
		fixRevert(root);
		at = traverse(root.left, at, order);
		order[at++] = root.value;
		return traverse(root.right, at, order);
	}

	static class Node {
		Node left;
		Node right;
		long priority;
		int value;
		boolean reversed;
		int size;

		public Node(int value) {
			this.value = value;
			priority = R.nextLong();
			size = 1;
		}
	}

	Pair<Node, Node> nullRes = Pair.makePair((Node)null, null);

	Pair<Node, Node> split(Node root, int by) {
		if (root == null) {
			return nullRes;
		}
		fixRevert(root);
		int size = size(root.left);
		if (size >= by) {
			Pair<Node, Node> result = split(root.left, by);
			root.left = result.second;
			fixSize(root);
			return Pair.makePair(result.first, root);
		} else {
			Pair<Node, Node> result = split(root.right, by - size - 1);
			root.right = result.first;
			fixSize(root);
			return Pair.makePair(root, result.second);
		}
	}

	Node merge(Node left, Node right) {
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		if (left.priority > right.priority) {
			fixRevert(left);
			left.right = merge(left.right, right);
			fixSize(left);
			return left;
		} else {
			fixRevert(right);
			right.left = merge(left, right.left);
			fixSize(right);
			return right;
		}
	}

	private void fixSize(Node root) {
		root.size = size(root.left) + size(root.right) + 1;
	}

	private void fixRevert(Node root) {
		if (root.reversed) {
			Node temp = root.left;
			root.left = root.right;
			root.right = temp;
			if (root.left != null) {
				root.left.reversed = !root.left.reversed;
			}
			if (root.right != null) {
				root.right.reversed = !root.right.reversed;
			}
			root.reversed = false;
		}
	}

	private int size(Node node) {
		return node == null ? 0 : node.size;
	}
}
