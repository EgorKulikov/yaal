package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BestBuggyRatings {
	static final long MASK = (1L << 32) - 1;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long m = in.readInt();
		long a = in.readInt();
		long b = in.readInt();
		long c = in.readInt();
		long d = in.readInt();
		int[] rating = IOUtils.readIntArray(in, n);
		Tree tree;
		try {
		tree = new Tree(rating);
		} catch (OutOfMemoryError e) {return;}
		int queryCount = in.readInt();
		int[] root = new int[queryCount + 1];
		long r1 = 0;
		long mask = MASK;
		for (int i = 0; i < queryCount; i++) {
			int index = (int) ((a * r1 + d) % (i + 1));
			int from = in.readInt();
			int to = in.readInt();
			long result = tree.query(root[index], from, to);
			r1 = result >> 32;
			long r2 = result & mask;
			int updatePosition = (int) ((b * r1 + d) % n);
			int updateValue = (int) ((c * r2 + d) % m);
			root[i + 1] = tree.update(root[i], updatePosition, updateValue);
			out.printLine(r1, r2);
		}
	}
}

class Tree {
	int n = 2000000;

	int[] left = new int[n];
	int[] right = new int[n];
	int[] m1 = new int[n];
	int[] m2 = new int[n];
	int size;
	int nextNode = 0;

	public Tree(int[] rating) {
		size = rating.length;
		init(0, size - 1, rating);
	}

	private int init(int l, int r, int[] rating) {
		int root = nextNode++;
		if (l == r)
			m1[root] = rating[l];
		else {
			int middle = (l + r) >> 1;
			left[root] = init(l, middle, rating);
			right[root] = init(middle + 1, r, rating);
			if (m1[left[root]] > m1[right[root]]) {
				m1[root] = m1[left[root]];
				m2[root] = Math.max(m2[left[root]], m1[right[root]]);
			} else {
				m1[root] = m1[right[root]];
				m2[root] = Math.max(m1[left[root]], m2[right[root]]);
			}
		}
		return root;
	}

	public int update(int root, int position, int value) {
		return update(root, 0, size - 1, position, value);
	}

	private int update(int root, int l, int r, int position, int value) {
		if (position > r || position < l)
			return root;
		m1[nextNode] = m1[root];
		m2[nextNode] = m2[root];
		left[nextNode] = left[root];
		right[nextNode] = right[root];
		root = nextNode++;
		if (l != r) {
			int middle = (l + r) >> 1;
			left[root] = update(left[root], l, middle, position, value);
			right[root] = update(right[root], middle + 1, r, position, value);
			if (m1[left[root]] > m1[right[root]]) {
				m1[root] = m1[left[root]];
				m2[root] = Math.max(m2[left[root]], m1[right[root]]);
			} else {
				m1[root] = m1[right[root]];
				m2[root] = Math.max(m1[left[root]], m2[right[root]]);
			}
		} else
			m1[root] = value;
		return root;
	}

	public long query(int root, int from, int to) {
		return query(root, 0, size - 1, from, to);
	}

	private long query(int root, int l, int r, int from, int to) {
		if (r < from || l > to)
			return 0;
		if (l >= from && r <= to)
			return (((long)m1[root]) << 32) + m2[root];
		int middle = (l + r) >> 1;
		long leftResult = query(left[root], l, middle, from, to);
		long rightResult = query(right[root], middle + 1, r, from, to);
		long m1l = leftResult >> 32;
		long m2l = leftResult & BestBuggyRatings.MASK;
		long m1r = rightResult >> 32;
		long m2r = rightResult & BestBuggyRatings.MASK;
		if (m1l > m1r)
			return (m1l << 32) + Math.max(m2l, m1r);
		else
			return (m1r << 32) + Math.max(m1l, m2r);
	}
}
