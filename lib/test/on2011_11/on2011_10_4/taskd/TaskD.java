package on2011_11.on2011_10_4.taskd;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] cost = new int[2];
		int[] capacity = new int[2];
		IOUtils.readIntArrays(in, cost, capacity);
		boolean reversed = false;
		if (capacity[0] < capacity[1]) {
			Collections.reverse(Array.wrap(cost));
			Collections.reverse(Array.wrap(capacity));
			reversed = true;
		}
		int maxFirst = Math.min(count / capacity[0], capacity[1] - 1);
		long minCost = Long.MAX_VALUE;
		int[] answer = new int[2];
		for (int i = 0; i <= maxFirst; i++) {
			if ((count - i * capacity[0]) % capacity[1] == 0) {
				int countOther = (count - i * capacity[0]) / capacity[1];
				long candidate = (long)i * cost[0] + (long)countOther * cost[1];
				if (candidate < minCost) {
					minCost = candidate;
					answer[0] = i;
					answer[1] = countOther;
				}
				int g = (int) IntegerUtils.gcd(capacity[0], capacity[1]);
				i += (countOther / (capacity[0] / g)) * (capacity[1] / g);
				countOther = (count - i * capacity[0]) / capacity[1];
				if (i * capacity[0] + countOther * capacity[1] != count)
					throw new RuntimeException();
				candidate = (long)i * cost[0] + (long)countOther * cost[1];
				if (candidate < minCost) {
					minCost = candidate;
					answer[0] = i;
					answer[1] = countOther;
				}
				break;
			}
		}
		if (minCost == Long.MAX_VALUE) {
			out.printLine("failed");
			return;
		}
		if (reversed)
			Collections.reverse(Array.wrap(answer));
		out.printLine(answer[0], answer[1]);
	}
}
