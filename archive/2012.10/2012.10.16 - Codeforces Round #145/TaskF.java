package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int queryCount = in.readInt();
		char[] s = in.readString().toCharArray();
		IntervalTree tree = new IntervalTree(s);
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int[] result = tree.query(from, to);
			int middleSymbol = -1;
			for (int j = 0; j < 26; j++) {
				if ((result[j] & 1) == 1) {
					if (middleSymbol == -1)
						middleSymbol = j;
					else
						middleSymbol = -2;
				}
			}
			if (middleSymbol == -2)
				continue;
			int middle = (from + to) >> 1;
			tree.update(from, middleSymbol == -1 ? middle : middle - 1, 1);
			tree.update(middle + 1, to, -1);
			if (middleSymbol != -1)
				tree.setSingle(middle, middleSymbol);
		}
		for (int i = 0; i < length; i++) {
			int[] result = tree.query(i, i);
			for (int j = 0; j < 26; j++) {
				if (result[j] == 1) {
					out.print((char)('a' + j));
					break;
				}
			}
		}
		out.printLine();
	}
}

class IntervalTree {
	protected int size;
    protected int[][] value;
    protected int[] delta;
	int[] result = new int[26];

	protected IntervalTree(char[] s) {
		this.size = s.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		value = new int[nodeCount][26];
        delta = new int[nodeCount];
		init(s);
	}

	public void init(char[] s) {
		init(0, 0, size - 1, s);
	}

	private void init(int root, int left, int right, char[] s) {
		if (left == right) {
			value[root][s[left] - 'a'] = 1;
        } else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, s);
			init(2 * root + 2, middle + 1, right, s);
			for (int i = 0; i < 26; i++)
				value[root][i] = value[2 * root + 1][i] + value[2 * root + 2][i];
		}
	}

	public void setSingle(int index, int value) {
		setSingle(0, index, value, 0, size - 1);
	}

	private void setSingle(int root, int index, int value, int left, int right) {
		if (index < left || index > right)
			return;
		if (left == right) {
			Arrays.fill(this.value[root], 0);
			this.value[root][value] = 1;
			return;
		}
		delta[root] = 0;
		int middle = (left + right) >> 1;
		setSingle(2 * root + 1, index, value, left, middle);
		setSingle(2 * root + 2, index, value, middle + 1, right);
		for (int i = 0; i < 26; i++)
			this.value[root][i] = this.value[2 * root + 1][i] + this.value[2 * root + 2][i];
	}

	public void update(int from, int to, int delta) {
		update(0, 0, size - 1, from, to, delta);
	}

	private void update(int root, int left, int right, int from, int to, int delta) {
        if (left > to || right < from)
            return;
        if (left >= from && right <= to) {
            this.delta[root] = delta;
			int skip = left - from;
			Arrays.fill(value[root], 0);
			int total = right - left + 1;
			if (delta == 1) {
				for (int i = 0; i < 26; i++) {
					int curSkip = Math.min(skip, result[i] >> 1);
					skip -= curSkip;
					int curAdd = Math.min(total, (result[i] >> 1) - curSkip);
					value[root][i] = curAdd;
					total -= curAdd;
				}
			} else if (delta == -1) {
				for (int i = 25; i >= 0; i--) {
					int curSkip = Math.min(skip, result[i] >> 1);
					skip -= curSkip;
					int curAdd = Math.min(total, (result[i] >> 1) - curSkip);
					value[root][i] = curAdd;
					total -= curAdd;
				}
			}
            return;
        }
		this.delta[root] = 0;
        int middle = (left + right) >> 1;
        update(2 * root + 1, left, middle, from, to, delta);
        update(2 * root + 2, middle + 1, right, from, to, delta);
		for (int i = 0; i < 26; i++)
			value[root][i] = value[2 * root + 1][i] + value[2 * root + 2][i];
    }

	public int[] query(int from, int to) {
		Arrays.fill(result, 0);
		query(0, 0, size - 1, from, to);
		return result;
	}

	private void query(int root, int left, int right, int from, int to) {
        if (left > to || right < from)
            return;
        if (left >= from && right <= to) {
			for (int i = 0; i < 26; i++)
				result[i] += value[root][i];
            return;
		}
		int middle = (left + right) >> 1;
		if (delta[root] != 0) {
			delta[2 * root + 1] = delta[root];
			delta[2 * root + 2] = delta[root];
			int length = middle - left + 1;
			if (delta[root] == 1) {
				for (int i = 0; i < 26; i++) {
					int cur = Math.min(length, value[root][i]);
					length -= cur;
					value[2 * root + 1][i] = cur;
					value[2 * root + 2][i] = value[root][i] - cur;
				}
			} else {
				for (int i = 25; i >= 0; i--) {
					int cur = Math.min(length, value[root][i]);
					length -= cur;
					value[2 * root + 1][i] = cur;
					value[2 * root + 2][i] = value[root][i] - cur;
				}
			}
			delta[root] = 0;
		}
        query(2 * root + 1, left, middle, from, to);
		query(2 * root + 2, middle + 1, right, from, to);
    }
}