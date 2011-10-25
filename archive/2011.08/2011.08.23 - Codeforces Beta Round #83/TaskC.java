import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	private int index = 0;
	private long[][] result;
	private int[][] mark;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		long index = in.readLong() - 2001;
		int restrictionCount = in.readInt();
		int[] from = new int[restrictionCount];
		int[] to = new int[restrictionCount];
		IOUtils.readIntArrays(in, from, to);
		int[] min = new int[count];
		int[] max = new int[count];
		Arrays.fill(max, count - 1);
		int mask = (1 << count) - 1;
		for (int i = 0; i < restrictionCount; i++) {
			from[i]--;
			to[i]--;
		}
		result = new long[1 << count][count + 1];
		mark = new int[1 << count][count + 1];
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(count, from, to);
		int[][] transposed = GraphUtils.buildSimpleOrientedGraph(count, to, from);
		long total = go(0, min, max, mask, transposed);
		if (total <= index) {
			out.println("The times have changed");
			return;
		}
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			for (int j = min[i]; j <= max[i]; j++) {
				if ((mask >> j & 1) == 0)
					continue;
				int[] copyMin = min.clone();
				int[] copyMax = max.clone();
				for (int k : graph[i])
					copyMin[k] = Math.max(copyMin[k], j + 1);
				for (int k : transposed[i])
					copyMax[k] = Math.min(copyMax[k], j - 1);
				long current = go(i + 1, copyMin, copyMax, mask - (1 << j), transposed);
				if (current > index) {
					answer[i] = j + 1;
					min = copyMin;
					max = copyMax;
					mask -= 1 << j;
					break;
				} else
					index -= current;
			}
		}
		IOUtils.printArray(answer, out);
	}

	private long go(int from, int[] min, int[] max, int mask, int[][] transposed) {
		index++;
		return go((1 << min.length) - (1 << from), 0, min, max, mask, transposed);
	}

	private long go(int positions, int step, int[] min, int[] max, int mask, int[][] transposed) {
		if (mark[positions][step] == index)
			return result[positions][step];
		mark[positions][step] = index;
		if (step == min.length)
			return result[positions][step] = 1;
		if (((mask >> step) & 1) == 0)
			return result[positions][step] = go(positions,  step + 1, min, max, mask, transposed);
		result[positions][step] = 0;
		for (int i = 0; i < min.length; i++) {
			if ((positions >> i & 1) == 0 || min[i] > step)
				continue;
			if (max[i] < step)
				return result[positions][step] = 0;
			boolean good = true;
			for (int vertex : transposed[i]) {
				if ((positions >> vertex & 1) != 0) {
					good = false;
					break;
				}
			}
			if (good)
				result[positions][step] += go(positions - (1 << i), step + 1, min, max, mask, transposed);
		}
		return result[positions][step];
	}
}

