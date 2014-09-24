package on2014_07.on2014_07_03_Weekly_Challenges___Week_6.Minimum_Average_Waiting_Time;



import net.egork.collections.comparators.ArrayLongComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class MinimumAverageWaitingTime {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] at = new long[count];
		long[] length = new long[count];
		IOUtils.readLongArrays(in, at, length);
		long currentTime = 0;
		long totalTime = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>(count, new ArrayLongComparator(length));
		int[] order = ArrayUtils.order(at);
		int j = 0;
		for (int i = 0; i < count; i++) {
			while (j < count && at[order[j]] <= currentTime) {
				queue.add(order[j++]);
			}
			if (queue.isEmpty()) {
				currentTime = at[order[j]];
				queue.add(order[j++]);
			}
			int current = queue.poll();
			currentTime += length[current];
			totalTime += currentTime - at[current];
		}
		out.printLine(totalTime / count);
    }
}
