package on2014_02.on2014_02_16_Rockethon_2014.C1___The_Tournament;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] points = new int[count];
		int[] cost = new int[count];
		IOUtils.readIntArrays(in, points, cost);
		if (required == count + 1) {
			out.printLine(0);
			return;
		}
		ArrayUtils.orderBy(points, cost);
		ArrayUtils.reverse(points);
		ArrayUtils.reverse(cost);
		int needPoints = points[required - 1];
		if (needPoints > count) {
			out.printLine(-1);
			return;
		}
		long answer = Long.MAX_VALUE;
		if (needPoints + 2 <= count) {
			int[] copy = cost.clone();
			Arrays.sort(copy);
			long candidate = 0;
			for (int i = 0; i < needPoints + 2; i++)
				candidate += copy[i];
			answer = candidate;
		}
		if (needPoints + 1 <= count) {
			int needWin = 0;
			for (int i = required - 1; i < count; i++) {
				if (points[i] == needPoints)
					needWin++;
			}
			boolean[] used = new boolean[count];
			int[] order = ArrayUtils.order(cost);
			long candidate = 0;
			int remaining = needPoints + 1 - needWin;
			for (int i : order) {
				if (points[i] == needPoints || points[i] == needPoints + 1) {
					used[i] = true;
					candidate += cost[i];
					if (--needWin == 0)
						break;
				}
			}
			for (int i : order) {
				if (remaining <= 0)
					break;
				if (!used[i]) {
					candidate += cost[i];
					remaining--;
				}
			}
			answer = Math.min(answer, candidate);
		}
		int needWin = 0;
		for (int i = required - 1; i < count; i++) {
			if (points[i] == needPoints || points[i] == needPoints - 1)
				needWin++;
		}
		boolean[] used = new boolean[count];
		int[] order = ArrayUtils.order(cost);
		long candidate = 0;
		int remaining = needPoints - needWin;
		for (int i : order) {
			if (points[i] == needPoints || points[i] == needPoints - 1) {
				used[i] = true;
				candidate += cost[i];
				if (--needWin == 0)
					break;
			}
		}
		for (int i : order) {
			if (remaining <= 0)
				break;
			if (!used[i]) {
				candidate += cost[i];
				remaining--;
			}
		}
		answer = Math.min(answer, candidate);
		out.printLine(answer);
    }
}
