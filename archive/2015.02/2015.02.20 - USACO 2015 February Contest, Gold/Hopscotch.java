package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Hopscotch {
	private static final int MOD = (int) (1e9 + 7);
	int[] qty;
	IntHashMap[] exact;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		in.readInt();
		int[][] map = IOUtils.readIntTable(in, rowCount, columnCount);
		qty = new int[columnCount * 4];
		exact = new IntHashMap[columnCount * 4];
		for (int i = 0; i < columnCount * 4; i++) {
			exact[i] = new IntHashMap();
		}
		add(0, 0, columnCount - 1, 0, map[0][0], 1);
		int[][] answer = new int[rowCount][columnCount];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				answer[i][j] = getQty(0, j - 1, 0, columnCount - 1) - getExact(0, j - 1, 0, columnCount - 1, map[i][j]);
				if (answer[i][j] < 0) {
					answer[i][j] += MOD;
				}
			}
			for (int j = 1; j < columnCount; j++) {
				add(0, 0, columnCount - 1, j, map[i][j], answer[i][j]);
			}
		}
		out.printLine(answer[rowCount - 1][columnCount - 1]);
    }

	private int getQty(int root, int to, int left, int right) {
		if (left > to) {
			return 0;
		}
		if (right <= to) {
			return qty[root];
		}
		int middle = (left + right) >> 1;
		int result = getQty(2 * root + 1, to, left, middle) + getQty(2 * root + 2, to, middle + 1, right);
		if (result >= MOD) {
			result -= MOD;
		}
		return result;
	}

	private int getExact(int root, int to, int left, int right, int index) {
		if (left > to) {
			return 0;
		}
		if (right <= to) {
			return exact[root].contains(index) ? exact[root].get(index) : 0;
		}
		int middle = (left + right) >> 1;
		int result = getExact(2 * root + 1, to, left, middle, index) +
			getExact(2 * root + 2, to, middle + 1, right, index);
		if (result >= MOD) {
			result -= MOD;
		}
		return result;
	}

	private void add(int root, int left, int right, int at, int index, int value) {
		if (at < left || at > right) {
			return;
		}
		qty[root] += value;
		if (qty[root] >= MOD) {
			qty[root] -= MOD;
		}
		int current = exact[root].contains(index) ? exact[root].get(index) : 0;
		current += value;
		if (current >= MOD) {
			current -= MOD;
		}
		exact[root].put(index, current);
		if (left != right) {
			int middle = (left + right) >> 1;
			add(2 * root + 1, left, middle, at, index, value);
			add(2 * root + 2, middle + 1, right, at, index, value);
		}
	}
}
