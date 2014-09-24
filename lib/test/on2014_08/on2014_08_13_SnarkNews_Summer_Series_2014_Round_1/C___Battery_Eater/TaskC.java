package on2014_08.on2014_08_13_SnarkNews_Summer_Series_2014_Round_1.C___Battery_Eater;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private int[][] next;
	private int[][] subNext;
	private int[][] answer;
	private int[][] subAnswer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		count++;
		x = Arrays.copyOf(x, count);
		y = Arrays.copyOf(y, count);
		next = new int[count][count];
		subNext = new int[count][count];
		final int[] distance = new int[count];
		int[] order = ArrayUtils.createOrder(count);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				distance[j] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
			}
			ArrayUtils.sort(order, new IntComparator() {
				@Override
				public int compare(int first, int second) {
					return distance[first] - distance[second];
				}
			});
			int last = -1;
			int realLast = -1;
			for (int j : order) {
				if (realLast == -1 || distance[realLast] != distance[j]) {
					last = realLast;
				}
				next[i][j] = last;
				subNext[i][j] = realLast;
				realLast = j;
			}
		}
		answer = new int[count][count];
		ArrayUtils.fill(answer, -1);
		subAnswer = new int[count][count];
		ArrayUtils.fill(subAnswer, -1);
		int result = 0;
		for (int i = 0; i < count; i++) {
			result = Math.max(result, go(i, count - 1));
		}
		out.printLine(result);
	}

	private int go(int current, int last) {
		if (current == last) {
			return 0;
		}
		if (current == -1) {
			return 0;
		}
		if (last == -1) {
			return 1;
		}
		if (answer[current][last] != -1) {
			return answer[current][last];
		}
		if (next[current][last] == next.length - 1) {
			return answer[current][last] = subGo(current, next[current][last]);
		}
		return answer[current][last] = Math.max(1 + go(next[current][last], current), subGo(current, next[current][last]));
	}

	private int subGo(int current, int last) {
		if (last == -1) {
			return 1;
		}
		if (subAnswer[current][last] != -1) {
			return subAnswer[current][last];
		}
		if (subNext[current][last] == next.length - 1) {
			return subAnswer[current][last] = subGo(current, subNext[current][last]);
		}
		return subAnswer[current][last] = Math.max(1 + go(subNext[current][last], current), subGo(current, subNext[current][last]));
	}
}
