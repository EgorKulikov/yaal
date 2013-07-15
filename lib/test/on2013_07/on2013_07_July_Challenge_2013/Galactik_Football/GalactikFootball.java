package on2013_07.on2013_07_July_Challenge_2013.Galactik_Football;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GalactikFootball {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		IndependentSetSystem setSystem = new ListIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(from[i], to[i]);
		if (setSystem.getSetCount() == 1) {
			out.printLine(0);
			return;
		}
		int[] cost = IOUtils.readIntArray(in, count);
		int[] min = new int[count];
		Arrays.fill(min, -1);
		for (int i = 0; i < count; i++)
			min[setSystem.get(i)] = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			if (cost[i] >= 0)
				min[setSystem.get(i)] = Math.min(min[setSystem.get(i)], cost[i]);
		}
		int totalMin = Integer.MAX_VALUE;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (min[i] == -1)
				continue;
			if (min[i] == Integer.MAX_VALUE) {
				out.printLine(-1);
				return;
			}
			answer += min[i];
			totalMin = Math.min(totalMin, min[i]);
		}
		answer += totalMin * (setSystem.getSetCount() - 2);
		out.printLine(answer);
    }
}
