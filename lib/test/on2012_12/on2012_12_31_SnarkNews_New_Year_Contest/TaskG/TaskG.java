package on2012_12.on2012_12_31_SnarkNews_New_Year_Contest.TaskG;



import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[][] keyboard = IOUtils.readTable(in, 3, 10);
		String text = in.readLine(false);
		try {
			if (text.isEmpty())
				text = in.readLine(false);
		} catch (Exception e) {
			out.printLine(0);
			return;
		}
		int[] result = new int[1 << 20];
		Arrays.fill(result, Integer.MAX_VALUE);
		result[2 + (2 << 2) + (2 << 4) + (2 << 6) + (2 << 12) + (2 << 14) + (2 << 16) + (2 << 18)] = 0;
		int[] next = new int[1 << 20];
		for (char c : text.toCharArray()) {
			if (c == ' ') {
				for (int i = 0; i < result.length; i++) {
					if (result[i] != Integer.MAX_VALUE)
						result[i]++;
				}
				continue;
			}
			Arrays.fill(next, Integer.MAX_VALUE);
			int row = -1;
			int column = -1;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 10; j++) {
					if (keyboard[i][j] == c) {
						row = i + 1;
						column = j;
					}
				}
			}
			for (int i = 0; i < result.length; i++) {
				if (result[i] == Integer.MAX_VALUE)
					continue;
				int left = 0;
				int right = 0;
				for (int j = 0; j <= column; j++) {
					if ((i >> (2 * j) & 3) == 0)
						left++;
				}
				for (int j = column; j < 10; j++) {
					if ((i >> (2 * j) & 3) == 0)
						right++;
				}
				if ((i >> (2 * column) & 3) != 0) {
					int k = i - ((i >> (2 * column) & 3) << (2 * column)) + (row << (2 * column));
					next[k] = Math.min(next[k], result[i] + Math.abs((i >> (2 * column) & 3) - row) + 1);
				}
				int k = i;
				int delta = 0;
				for (int j = 0; j < left; j++) {
					boolean found = false;
					for (int l = column + 1; l < 10; l++) {
						if ((k >> (2 * l) & 3) != 0) {
							int current = k >> (2 * l) & 3;
							found = true;
							k -= current << (2 * l);
							l--;
							delta++;
							while ((k >> (2 * l) & 3) != 0 || l > column) {
								delta++;
								int nxt = k >> (2 * l) & 3;
								if (nxt != 0) {
									k -= nxt << (2 * l);
									k += current << (2 * l);
									current = nxt;
								}
								l--;
							}
							k += current << (2 * l);
							break;
						}
					}
					if (!found)
						break;
					int kk = k - ((k >> (2 * column) & 3) << (2 * column)) + (row << (2 * column));
					next[kk] = Math.min(next[kk], result[i] + Math.abs((k >> (2 * column) & 3) - row) + 1 + delta);
				}
				k = i;
				delta = 0;
				for (int j = 0; j < right; j++) {
					boolean found = false;
					for (int l = column - 1; l >= 0; l--) {
						if ((k >> (2 * l) & 3) != 0) {
							int current = k >> (2 * l) & 3;
							found = true;
							k -= current << (2 * l);
							l++;
							delta++;
							while ((k >> (2 * l) & 3) != 0 || l < column) {
								delta++;
								int nxt = k >> (2 * l) & 3;
								if (nxt != 0) {
									k -= nxt << (2 * l);
									k += current << (2 * l);
									current = nxt;
								}
								l++;
							}
							k += current << (2 * l);
							break;
						}
					}
					if (!found)
						break;
					int kk = k - ((k >> (2 * column) & 3) << (2 * column)) + (row << (2 * column));
					next[kk] = Math.min(next[kk], result[i] + Math.abs((k >> (2 * column) & 3) - row) + 1 + delta);
				}
			}
			int[] temp = result;
			result = next;
			next = temp;
		}
		out.printLine(new IntArray(result).min());
	}
}
