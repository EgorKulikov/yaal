package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TravelingPlan {
	int count, lastTime;
	int[] from, to, fromTime, toTime;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		if (count == 1) {
			out.printLine(0);
			return;
		}
		lastTime = in.readInt();
		int lineCount = in.readInt();
		from = new int[lineCount];
		fromTime = new int[lineCount];
		to = new int[lineCount];
		toTime = new int[lineCount];
		IOUtils.readIntArrays(in, from, to, fromTime, toTime);
		MiscUtils.decreaseByOne(from, to);
		if (!canArrive(lastTime)) {
			out.printLine(-1);
			return;
		}
		int left = 0;
		int right = lastTime;
		while (left < right) {
			int middle = (left + right) >> 1;
			if (canArrive(middle))
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }

	boolean canArrive(int maxTime) {
		int[][] timeArrive = GraphUtils.buildSimpleOrientedGraph(count, to, from);
		int[] size = new int[count];
		timeArrive[0] = new int[timeArrive[0].length + 1];
		size[0] = 1;
		int[] order = ArrayUtils.order(toTime);
		for (int i : order) {
			if (toTime[i] > lastTime)
				break;
			int position = Arrays.binarySearch(timeArrive[from[i]], 0, size[from[i]], fromTime[i]);
			if (position < 0)
				position = -position - 2;
			if (position == -1)
				continue;
			if (fromTime[i] - timeArrive[from[i]][position] > maxTime)
				continue;
			timeArrive[to[i]][size[to[i]]++] = toTime[i];
		}
		return size[count - 1] > 0;
	}
}
