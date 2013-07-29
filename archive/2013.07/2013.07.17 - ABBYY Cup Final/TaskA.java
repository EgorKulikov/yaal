package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] quality = IOUtils.readIntArray(in, count);
		Map<Integer, Integer> last = new EHashMap<Integer, Integer>();
		long[] sumPositive = new long[count];
		long sum = 0;
		for (int i = 0; i < count; i++) {
			last.put(quality[i], i);
			if (quality[i] > 0)
				sum += quality[i];
			sumPositive[i] = sum;
		}
		int from = -1;
		int to = -1;
		long answer = Long.MIN_VALUE;
		for (int i = 0; i < count; i++) {
			int curLast = last.get(quality[i]);
			if (curLast != i && answer < 2 * quality[i] + sumPositive[curLast - 1] - sumPositive[i]) {
				answer = 2 * quality[i] + sumPositive[curLast - 1] - sumPositive[i];
				from = i;
				to = curLast;
			}
		}
		IntList toKill = new IntArrayList();
		for (int i = 0; i < from; i++)
			toKill.add(i + 1);
		for (int i = from + 1; i < to; i++) {
			if (quality[i] < 0)
				toKill.add(i + 1);
		}
		for (int i = to + 1; i < count; i++)
			toKill.add(i + 1);
		out.printLine(answer, toKill.size());
		out.printLine(toKill);
    }
}
