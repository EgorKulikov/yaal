package on2015_08.on2015_08_30_XV_Open_Cup_Onsite.G___Car_Washes;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
	int[] value;
	int[] a;
	int[] b;
	int[] c;
	int m;
	long[][][] answer;
	long[] qty;
	int[][][] split;

	int[] cost;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		m = in.readInt();
		a = new int[m];
		b = new int[m];
		c = new int[m];
		IOUtils.readIntArrays(in, a, b, c);
		MiscUtils.decreaseByOne(a, b);
		value = ArrayUtils.compress(c);
		answer = new long[n][n][];
		split = new int[n][n][value.length];
		ArrayUtils.fill(split, -2);
		qty = new long[value.length];
		calculate(0, n - 1);
		out.printLine(answer[0][n - 1][0]);
		cost = new int[n];
		fillCost(0, n - 1, 0);
		out.printLine(cost);
	}

	private void fillCost(int left, int right, int at) {
		if (left > right) {
			return;
		}
		if (split[left][right][at] == -2) {
			Arrays.fill(cost, left, right + 1, value[at]);
			return;
		}
		if (split[left][right][at] == -1) {
			fillCost(left, right, at + 1);
			return;
		}
		cost[split[left][right][at]] = value[at];
		fillCost(left, split[left][right][at] - 1, at);
		fillCost(split[left][right][at] + 1, right, at);
	}

	private void calculate(int left, int right) {
		if (answer[left][right] != null) {
			return;
		}
		answer[left][right] = new long[value.length];
		for (int i = left; i <= right; i++) {
			if (i != left) {
				calculate(left, i - 1);
			}
			if (i != right) {
				calculate(i + 1, right);
			}
		}
		for (int i = left; i <= right; i++) {
			Arrays.fill(qty, 0);
			for (int j = 0; j < m; j++) {
				if (a[j] >= left && a[j] <= i && b[j] >= i && b[j] <= right) {
					qty[c[j]]++;
				}
			}
			for (int j = qty.length - 2; j >= 0; j--) {
				qty[j] += qty[j + 1];
			}
			for (int j = 0; j < value.length; j++) {
				long toLeft = i == left ? 0 : answer[left][i - 1][j];
				long toRight = i == right ? 0 : answer[i + 1][right][j];
				long candidate = toLeft + toRight + qty[j] * value[j];
				if (answer[left][right][j] < candidate) {
					answer[left][right][j] = candidate;
					split[left][right][j] = i;
				}
			}
		}
		for (int j = value.length - 2; j >= 0; j--) {
			if (answer[left][right][j] < answer[left][right][j + 1]) {
				answer[left][right][j] = answer[left][right][j + 1];
				split[left][right][j] = -1;
			}
		}
	}
}
