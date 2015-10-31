package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndTheNumberSequence {
	private static final int MOD = (int) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int l = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] current = new int[1 << n];
		int[] next = new int[1 << n];
		int[] qty = new int[k + 1];
		for (int i : a) {
			qty[i]++;
		}
		int[][] positions = new int[k + 1][];
		for (int i = 1; i <= k; i++) {
			positions[i] = new int[qty[i]];
		}
		for (int i = 0; i < n; i++) {
			positions[a[i]][--qty[a[i]]] = i;
		}
		current[0] = 1;
		for (int i = 0; i < n; i++) {
			Arrays.fill(next, 0);
			for (int j = 0; j < (1 << n); j++) {
				for (int m = 1; m <= k; m++) {
					int cMask = j;
					for (int o : positions[m]) {
						int down = cMask >> o;
						cMask -= Integer.lowestOneBit(down) << o;
						cMask += 1 << o;
					}
					next[cMask] += current[j];
					if (next[cMask] >= MOD) {
						next[cMask] -= MOD;
					}
				}
			}
			int[] temp = current;
			current = next;
			next = temp;
		}
		long answer = 0;
		for (int i = 0; i < (1 << n); i++) {
			if (Integer.bitCount(i) == l) {
				answer += current[i];
			}
		}
		out.printLine(answer % MOD);
	}
}
