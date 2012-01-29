package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int queryCount = in.readInt();
		IntervalTree tree = new IntervalTree(IOUtils.readCharArray(in, length));
		for (int i = 0; i < queryCount; i++) {
			String command = in.readString();
			if ("count".equals(command))
				out.printLine(tree.query());
			else {
				int from = in.readInt() - 1;
				int to = in.readInt();
				tree.switchState(from, to);
			}
		}
	}
}

class IntervalTree {
	int[] left, right, count4, count7, count47, count74;
	boolean[] switched;

	IntervalTree(char[] sequence) {
		int arraySize = Integer.highestOneBit(sequence.length) << 2;
		left = new int[arraySize];
		right = new int[arraySize];
		count4 = new int[arraySize];
		count47 = new int[arraySize];
		count7 = new int[arraySize];
		count74 = new int[arraySize];
		switched = new boolean[arraySize];
		initTree(0, 0, sequence.length, sequence);
	}

	private void initTree(int root, int left, int right, char[] sequence) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left == 1) {
			count47[root] = count74[root] = 1;
			if (sequence[left] == '4')
				count4[root] = 1;
			else
				count7[root] = 1;
		} else {
			initTree(2 * root + 1, left, (left + right) >> 1, sequence);
			initTree(2 * root + 2, (left + right) >> 1, right, sequence);
			update(root);
		}
	}

	public void switchState(int from, int to) {
		switchState(0, from, to);
	}

	private void switchState(int root, int from, int to) {
		if (left[root] >= to || right[root] <= from)
			return;
		if (left[root] >= from && right[root] <= to) {
			switched[root] ^= true;
			return;
		}
		if (switched[root]) {
			switched[2 * root + 1] ^= true;
			switched[2 * root + 2] ^= true;
			switched[root] = false;
		}
		switchState(2 * root + 1, from, to);
		switchState(2 * root + 2, from, to);
		update(root);
	}

	public int query() {
		return get47(0);
	}

	private void update(int root) {
		count4[root] = get4(2 * root + 1) + get4(2 * root + 2);
		count7[root] = get7(2 * root + 1) + get7(2 * root + 2);
		count47[root] = Math.max(get4(2 * root + 1) + get47(2 * root + 2),
			get47(2 * root + 1) + get7(2 * root + 2));
		count74[root] = Math.max(get7(2 * root + 1) + get74(2 * root + 2),
			get74(2 * root + 1) + get4(2 * root + 2));
	}

	private int get4(int root) {
		if (switched[root])
			return count7[root];
		else
			return count4[root];
	}

	private int get7(int root) {
		if (!switched[root])
			return count7[root];
		else
			return count4[root];
	}

	private int get47(int root) {
		if (switched[root])
			return count74[root];
		else
			return count47[root];
	}

	private int get74(int root) {
		if (switched[root])
			return count47[root];
		else
			return count74[root];
	}
}