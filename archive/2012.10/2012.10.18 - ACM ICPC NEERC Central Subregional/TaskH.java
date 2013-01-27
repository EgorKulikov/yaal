package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] colors = IOUtils.readIntArray(in, count);
		IntervalTree tree = new IntervalTree(colors);
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			out.printLine(tree.query(from, to));
		}
	}
}

class IntervalTree {
	protected int size;
    protected long[][] value;
	protected long[][] result;

	protected IntervalTree(int[] array) {
		this.size = array.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		value = new long[4][nodeCount];
		result = new long[4][nodeCount];
		init(array);
	}

	public void init(int[] array) {
		init(0, 0, size - 1, array);
	}

	private void init(int root, int left, int right, int[] array) {
		if (left == right) {
			value[array[left] >> 6][root] = 1L << (array[left] & 63);
        } else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, array);
			init(2 * root + 2, middle + 1, right, array);
			for (int i = 0; i < 4; i++)
				value[i][root] = value[i][2 * root + 1] | value[i][2 * root + 2];
		}
	}

	public int query(int from, int to) {
		query(0, 0, size - 1, from, to);
		int answer = 0;
		for (int i = 0; i < 4; i++)
			answer += Long.bitCount(result[i][0]);
		return answer;
	}

	private void query(int root, int left, int right, int from, int to) {
        if (left > to || right < from) {
			for (int i = 0; i < 4; i++)
				result[i][root] = 0;
            return;
		}
        if (left >= from && right <= to) {
			for (int i = 0; i < 4; i++)
				result[i][root] = value[i][root];
            return;
		}
        int middle = (left + right) >> 1;
        query(2 * root + 1, left, middle, from, to);
		query(2 * root + 2, middle + 1, right, from, to);
		for (int i = 0; i < 4; i++)
			result[i][root] = result[i][2 * root + 1] | result[i][2 * root + 2];
    }
}