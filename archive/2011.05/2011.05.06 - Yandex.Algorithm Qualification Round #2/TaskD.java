import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int queueSize = in.readInt();
		int[] time = IOUtils.readIntArray(in, queueSize);
		int[][] result = new int[queueSize + 1][queueSize];
		int[][] last = new int[queueSize + 1][queueSize];
		int[][] first = new int[queueSize + 1][queueSize];
		int[][] second = new int[queueSize + 1][queueSize];
		ArrayUtils.fill(result, Integer.MAX_VALUE);
		result[1][0] = 0;
		int answer = Integer.MAX_VALUE;
		int lastIndex = -1;
		for (int i = 1; i < queueSize; i += 2) {
			for (int j = 0; j < i; j++) {
				if (i == queueSize - 1) {
					if (answer > result[i][j] + Math.max(time[j], time[i])) {
						answer = result[i][j] + Math.max(time[j], time[i]);
						lastIndex = j;
					}
				} else {
					if (result[i + 2][j] > result[i][j] + Math.max(time[i], time[i + 1])) {
						result[i + 2][j] = result[i][j] + Math.max(time[i], time[i + 1]);
						last[i + 2][j] = j;
						first[i + 2][j] = i;
						second[i + 2][j] = i + 1;
					}
					if (result[i + 2][i] > result[i][j] + Math.max(time[j], time[i + 1])) {
						result[i + 2][i] = result[i][j] + Math.max(time[j], time[i + 1]);
						last[i + 2][i] = j;
						first[i + 2][i] = j;
						second[i + 2][i] = i + 1;
					}
					if (result[i + 2][i + 1] > result[i][j] + Math.max(time[j], time[i])) {
						result[i + 2][i + 1] = result[i][j] + Math.max(time[j], time[i]);
						last[i + 2][i + 1] = j;
						first[i + 2][i + 1] = j;
						second[i + 2][i + 1] = i;
					}
				}
			}
		}
		if (queueSize % 2 == 1) {
			for (int i = 0; i < queueSize; i++) {
				if (answer > result[queueSize][i] + time[i]) {
					answer = result[queueSize][i] + time[i];
					lastIndex = i;
				}
			}
		}
		out.println(answer);
		List<Pair<Integer, Integer>> path = new ArrayList<Pair<Integer, Integer>>();
		int index = queueSize - (1 - queueSize % 2);
		int originalLast = lastIndex;
		if (queueSize % 2 == 0)
			path.add(Pair.makePair(lastIndex + 1, queueSize));
		while (index != 1) {
			path.add(Pair.makePair(first[index][lastIndex] + 1, second[index][lastIndex] + 1));
			lastIndex = last[index][lastIndex];
			index -= 2;
		}
		Collections.reverse(path);
		for (Pair<Integer, Integer> element : path)
			out.println(element.first + " " + element.second);
		if (queueSize % 2 == 1)
			out.println(originalLast + 1);
	}
}

