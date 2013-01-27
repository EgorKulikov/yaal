package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MartianCoinGame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heads = IOUtils.readIntArray(in, count);
		Tree tree = new Tree(heads);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (type == 0) {
				tree.query(from, to);
				out.printLine(tree.resH1[0], tree.resH2[0], tree.resT1[0], tree.resT2[0]);
			} else if (type == 1)
				tree.flip(from, to);
			else
				tree.sw(from, to);
		}
	}
}

class Tree {
	int[] t1, t2, h1, h2;
	int[] resT1, resT2, resH1, resH2;
	boolean[] f, s;
	int length;

	public Tree(int[] heads) {
		length = heads.length;
		int nodeCount = heads.length * 4;
		t1 = new int[nodeCount];
		t2 = new int[nodeCount];
		h1 = new int[nodeCount];
		h2 = new int[nodeCount];
		resT1 = new int[nodeCount];
		resT2 = new int[nodeCount];
		resH1 = new int[nodeCount];
		resH2 = new int[nodeCount];
		f = new boolean[nodeCount];
		s = new boolean[nodeCount];
		init(0, 0, length - 1, heads);
	}

	private void init(int root, int left, int right, int[] heads) {
		if (left == right) {
			if (heads[left] == 0)
				t1[root] = 1;
			else
				h1[root] = 1;
		} else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, heads);
			init(2 * root + 2, middle + 1, right, heads);
			t1[root] = t1[2 * root + 1] + t1[2 * root + 2];
			h1[root] = h1[2 * root + 1] + h1[2 * root + 2];
		}
	}

	void query(int from, int to) {
		query(0, 0, length - 1, from, to);
	}

	void flip(int from, int to) {
		flip(0, 0, length - 1, from, to);
	}

	void sw(int from, int to) {
		sw(0, 0, length - 1, from, to);
	}

	private void flip(int root, int left, int right, int from, int to) {
		if (from > right || to < left)
			return;
		if (from <= left && right <= to) {
			flip(root);
			return;
		}
		if (f[root]) {
			f[root] = false;
			flip(2 * root + 1);
			flip(2 * root + 2);
		}
		if (s[root]) {
			s[root] = false;
			sw(2 * root + 1);
			sw(2 * root + 2);
		}
		int middle = (left + right) >> 1;
		flip(2 * root + 1, left, middle, from, to);
		flip(2 * root + 2, middle + 1, right, from, to);
		t1[root] = t1[2 * root + 1] + t1[2 * root + 2];
		t2[root] = t2[2 * root + 1] + t2[2 * root + 2];
		h1[root] = h1[2 * root + 1] + h1[2 * root + 2];
		h2[root] = h2[2 * root + 1] + h2[2 * root + 2];
	}

	private void sw(int root, int left, int right, int from, int to) {
		if (from > right || to < left)
			return;
		if (from <= left && right <= to) {
			sw(root);
			return;
		}
		if (f[root]) {
			f[root] = false;
			flip(2 * root + 1);
			flip(2 * root + 2);
		}
		if (s[root]) {
			s[root] = false;
			sw(2 * root + 1);
			sw(2 * root + 2);
		}
		int middle = (left + right) >> 1;
		sw(2 * root + 1, left, middle, from, to);
		sw(2 * root + 2, middle + 1, right, from, to);
		t1[root] = t1[2 * root + 1] + t1[2 * root + 2];
		t2[root] = t2[2 * root + 1] + t2[2 * root + 2];
		h1[root] = h1[2 * root + 1] + h1[2 * root + 2];
		h2[root] = h2[2 * root + 1] + h2[2 * root + 2];
	}

	private void query(int root, int left, int right, int from, int to) {
		if (from > right || to < left) {
			resT1[root] = resT2[root] = resH1[root] = resH2[root] = 0;
			return;
		}
		if (from <= left && right <= to) {
			resT1[root] = t1[root];
			resT2[root] = t2[root];
			resH1[root] = h1[root];
			resH2[root] = h2[root];
			return;
		}
		if (f[root]) {
			f[root] = false;
			flip(2 * root + 1);
			flip(2 * root + 2);
		}
		if (s[root]) {
			s[root] = false;
			sw(2 * root + 1);
			sw(2 * root + 2);
		}
		int middle = (left + right) >> 1;
		query(2 * root + 1, left, middle, from, to);
		query(2 * root + 2, middle + 1, right, from, to);
		resT1[root] = resT1[2 * root + 1] + resT1[2 * root + 2];
		resT2[root] = resT2[2 * root + 1] + resT2[2 * root + 2];
		resH1[root] = resH1[2 * root + 1] + resH1[2 * root + 2];
		resH2[root] = resH2[2 * root + 1] + resH2[2 * root + 2];
	}

	private void flip(int root) {
		int temp = t1[root];
		t1[root] = h1[root];
		h1[root] = temp;
		temp = t2[root];
		t2[root] = h2[root];
		h2[root] = temp;
		f[root] = !f[root];
	}

	private void sw(int root) {
		int temp = t1[root];
		t1[root] = t2[root];
		t2[root] = temp;
		temp = h1[root];
		h1[root] = h2[root];
		h2[root] = temp;
		s[root] = !s[root];
	}
}