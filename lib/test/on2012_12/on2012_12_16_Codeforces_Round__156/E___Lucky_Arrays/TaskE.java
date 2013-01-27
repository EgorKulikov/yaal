package on2012_12.on2012_12_16_Codeforces_Round__156.E___Lucky_Arrays;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskE {
	private static final long MOD = 777777777;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		boolean[][] isLucky = new boolean[4][4];
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++)
				isLucky[i][j] = in.readInt() == 1;
		}
		long[][][] variants = new long[4][4][count + 1];
		for (int i = 1; i < 4; i++) {
			variants[i][i][1] = 1;
			variants[0][i][1] = 1;
			variants[i][0][1] = 1;
		}
		variants[0][0][1] = 3;
		for (int i = 2; i <= count; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 1; k < 4; k++) {
					for (int l = 1; l < 4; l++) {
						if (isLucky[k][l]) {
							variants[j][l][i] += variants[j][k][i - 1];
							variants[j][0][i] += variants[j][k][i - 1];
						}
					}
				}
				for (int k = 0; k < 4; k++)
					variants[j][k][i] %= MOD;
			}
		}
		NavigableSet<Integer> set = new TreeSet<Integer>();
		int[] state = new int[count];
		LongIntervalTree tree = new LongIntervalTree(count + 1) {
			@Override
			protected long joinValue(long left, long right) {
				return left * right % MOD;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				if (was == -1)
					return delta;
				if (delta == -1)
					return was;
				return was * delta % MOD;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == -1)
					return value;
				return delta;
			}

			@Override
			protected long neutralValue() {
				return 1;
			}

			@Override
			protected long neutralDelta() {
				return -1;
			}
		};
		tree.init();
		set.add(-1);
		set.add(count);
		tree.update(0, 0, variants[0][0][count]);
		tree.query(0, 0);
		for (int i = 0; i < queryCount; i++) {
			int index = in.readInt() - 1;
			int value = in.readInt();
			if (value != 0) {
				int left = set.lower(index);
				int leftState = left == -1 ? 0 : state[left];
				int leftLength = left == -1 ? index + 1 : index - left + 1;
				int right = set.higher(index);
				int rightState = right == count ? 0 : state[right];
				int rightLength = right == count ? right - index : right - index + 1;
				tree.update(left + 1, left + 1, variants[leftState][value][leftLength]);
				tree.update(index + 1, index + 1, variants[value][rightState][rightLength]);
			} else if (state[index] != 0) {
				tree.update(index + 1, index + 1, 1);
				int left = set.lower(index);
				int leftState = left == -1 ? 0 : state[left];
				int right = set.higher(index);
				int rightState = right == count ? 0 : state[right];
				int length = right - left + 1;
				if (left == -1)
					length--;
				if (right == count)
					length--;
				tree.update(left + 1, left + 1, variants[leftState][rightState][length]);
			}
			state[index] = value;
			if (value == 0)
				set.remove(index);
			else
				set.add(index);
			out.printLine(tree.query(0, count));
		}
	}
}
