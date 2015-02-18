package on2014_10.on2014_10_12_Codeforces_Round__272__Div__1_.D___Dreamoon_and_Binary;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] x = in.readString().toCharArray();
		long[][] qty = new long[x.length + 1][x.length + 1];
		int[][] prints = new int[x.length + 1][x.length + 1];
		int[][] lcp = new int[x.length + 1][x.length + 1];
		boolean[][] nonZero = new boolean[x.length + 1][x.length + 1];
		ArrayUtils.fill(prints, Integer.MAX_VALUE / 2);
		prints[0][0] = 1;
		qty[0][0] = 1;
		nonZero[0][0] = true;
		StringHash hash = new SimpleStringHash(new String(x));
		int[] order = ArrayUtils.createOrder(x.length);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				int left = commonPrefix(first, second, hash);
				if (left == Math.min(x.length - first, x.length - second)) {
					return second - first;
				}
				return x[first + left] - x[second + left];
			}
		});
		int[] position = ArrayUtils.reversePermutation(order);
		int[] common = new int[x.length - 1];
		for (int i = 0; i < x.length - 1; i++) {
			common[i] = commonPrefix(order[i], order[i + 1], hash);
		}
		for (int i = 0; i < x.length; i++) {
			int current = Integer.MAX_VALUE;
			for (int j = i + 1; j < x.length; j++) {
				current = Math.min(current, common[j - 1]);
				lcp[i][j] = lcp[j][i] = current;
			}
		}
		for (int i = 1; i <= x.length; i++) {
			for (int j = Math.max(i - 1, 0); j >= 0; j--) {
				nonZero[i][j] |= nonZero[i - 1][j];
				prints[i][j] = Math.min(prints[i][j], prints[i - 1][j]);
				qty[i][j] += qty[i - 1][j];
				if (qty[i][j] >= MOD) {
					qty[i][j] -= MOD;
				}
				if (i == x.length || x[i] != '1') {
					continue;
				}
				int next = 2 * i - j;
				if (position[i] < position[j] && lcp[position[i]][position[j]] < i - j) {
					next++;
				}
				if (next > x.length) {
					continue;
				}
				nonZero[next][i] |= nonZero[i][j];
				prints[next][i] = Math.min(prints[next][i], prints[i][j] + 1);
				qty[next][i] += qty[i][j];
				if (qty[next][i] >= MOD) {
					qty[next][i] -= MOD;
				}
			}
		}
		long totalQty = 0;
		long minOps = Integer.MAX_VALUE;
		boolean init = false;
		for (int i = x.length - 1; i >= 0; i--) {
			if (x[i] == '1' && nonZero[x.length][i]) {
				totalQty += qty[x.length][i];
				if (!init || x.length - i <= 32) {
					long current = 0;
					boolean overflow = false;
					for (int j = i; j < x.length; j++) {
						current *= 2;
						current += x[j] - '0';
						if (current >= MOD) {
							overflow = true;
							current -= MOD;
						}
					}
					current += prints[x.length][i];
					if (current >= MOD) {
						overflow = true;
						current -= MOD;
					}
					if (current < minOps && !overflow || !init) {
						minOps = current;
					}
					init = true;
				}
			}
		}
		out.printLine(totalQty % MOD);
		out.printLine(minOps);
    }

	protected int commonPrefix(int first, int second, StringHash hash) {
		int left = 0;
		int right = Math.min(hash.length() - first, hash.length() - second);
		while (left < right) {
			int middle = (left + right + 1) >> 1;
			if (hash.hash(first, first + middle) == hash.hash(second, second + middle)) {
				left = middle;
			} else {
				right = middle - 1;
			}
		}
		return left;
	}
}
