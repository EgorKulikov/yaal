import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class StrPack implements Solver {
	private long[] result = new long[40 * shift1];
	private int[] last = new int[40 * shift1];
	private int[] mark = new int[40 * shift1];
	private static int shift2 = 101;
	private static int shift1 = 101 * shift2;
	private int maxLength, testNumber;
	private int[] order;
	private long[] freq;
	private long[] delta = new long[30 * 101];

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		this.testNumber = testNumber;
		int size = in.readInt();
		maxLength = in.readInt();
		if (size == 0 && maxLength == 0) {
			Exit.exit(in, out);
			return;
		}
		freq = new long[size];
		for (int i = 0; i < size; i++)
			freq[i] = in.readInt();
//		long[][][] result = new long[maxLength][size + 1][size + 1];
//		int[][][] last = new int[maxLength][size + 1][size + 1];
		if (size == 1) {
			out.println(freq[0]);
			out.println(1);
			return;
		}
		Integer[] order = new Integer[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Long.signum(freq[o2] - freq[o1]);
			}
		});
		this.order = new int[size];
		for (int i = 0; i < size; i++)
			this.order[i] = order[i];
		for (int j = 0; j < size; j++)
			delta[j] = freq[order[j]];
		int startKey = 2;
		mark[startKey] = testNumber;
		result[startKey] = 0;
		for (int level = 0; level < maxLength; ++level) {
			for (int index = 0; index < freq.length; ++index) {
				int key = level * shift1 + index * shift2;
				for (int occupied = 0; occupied <= freq.length - index; ++occupied, ++key) {
					if (mark[key] == testNumber) {
						long curRes = result[key];
						if (level + 1 < maxLength) {
							int nextKey = (level + 1) * shift1 + index * shift2 + Math.min(2 * occupied, freq.length - index);
							if (mark[nextKey] < testNumber || result[nextKey] > curRes) {
								mark[nextKey] = testNumber;
								result[nextKey] = curRes;
								last[nextKey] = -1 - occupied;
							}
						}
						if (occupied > 0) {
							int nextKey = key + shift2 - 1;
							curRes += delta[index] * (level + 1);
							if (mark[nextKey] < testNumber || result[nextKey] > curRes) {
								mark[nextKey] = testNumber;
								result[nextKey] = curRes;
								last[nextKey] = order[index];
							}
						}
					}
				}
			}
		}
		long answer = Long.MAX_VALUE;
		int curLevel = -1;
		int curIndex = freq.length;
		int curOccupied = 0;
		for (int level = 0; level < maxLength; ++level) {
			int index = freq.length;
			int key = level * shift1 + index * shift2;
			if (mark[key] == testNumber) {
				if (result[key] < answer) {
					answer = result[key];
					curLevel = level;
				}
			}
		}
		if (answer == Long.MAX_VALUE)
			throw new RuntimeException();
		out.println(answer);
		int[] length = new int[size];
		while (curIndex > 0) {
			int curLast = last[curLevel * shift1 + curIndex * shift2 + curOccupied];
			if (curLast < 0) {
				curLevel--;
				curOccupied = -curLast - 1;
			} else {
				length[curLast] = curLevel + 1;
				curIndex--;
				curOccupied++;
			}
		}
		out.print(length[0]);
		for (int i = 1; i < size; i++)
			out.print(" " + length[i]);
		out.println();
	}
}

