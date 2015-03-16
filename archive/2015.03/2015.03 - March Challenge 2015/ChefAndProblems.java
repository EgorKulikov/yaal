package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndProblems {
	public static final int STEP = 1000;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int max = in.readInt();
		int queryCount = in.readInt();
		int[] height = IOUtils.readIntArray(in, count);
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(height, from, to);
		int[] answer = new int[queryCount];
		int[][] result = new int[(count + STEP - 1) / STEP][count];
		int[] first = new int[max];
		for (int i = 0; i < count; i += STEP) {
			int id = i / STEP;
			Arrays.fill(first, -1);
			int current = 0;
			for (int j = i; j < count; j++) {
				if (first[height[j]] != -1) {
					current = Math.max(current, j - first[height[j]]);
				} else {
					first[height[j]] = j;
				}
				result[id][j] = current;
			}
		}
		int[] last = new int[max];
		Arrays.fill(last, -1);
		int[] order = ArrayUtils.order(to);
		int at = 0;
		for (int i : order) {
			while (at <= to[i]) {
				last[height[at]] = at;
				at++;
			}
			int threshold = (from[i] + STEP - 1) / STEP;
			if (threshold * STEP <= to[i]) {
				answer[i] = result[threshold][to[i]];
			}
			for (int j = from[i]; j <= to[i] && j < threshold * STEP; j++) {
				answer[i] = Math.max(answer[i], last[height[j]] - j);
			}
		}
		for (int i : answer) {
			out.printLine(i);
		}
    }
}
