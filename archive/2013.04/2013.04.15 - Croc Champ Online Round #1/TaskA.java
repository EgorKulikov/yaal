package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] time = new int[count];
		int[] qty = new int[count];
		IOUtils.readIntArrays(in, time, qty);
		int currentTime = 0;
		int queueSize = 0;
		int maxQueue = 0;
		for (int i = 0; i < count; i++) {
			queueSize = Math.max(0, queueSize + currentTime - time[i]) + qty[i];
			currentTime = time[i];
			maxQueue = Math.max(maxQueue, queueSize);
		}
		out.printLine(currentTime + queueSize, maxQueue);
    }
}
