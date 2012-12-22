package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] distance = new int[count];
		int[] length = new int[count];
		IOUtils.readIntArrays(in, distance, length);
		int totalDistance = in.readInt();
		int[] max = new int[count];
		max[0] = distance[0];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (distance[i] + max[i] >= distance[j])
					max[j] = Math.max(max[j], Math.min(distance[j] - distance[i], length[j]));
			}
			if (distance[i] + max[i] >= totalDistance) {
				out.printLine("Case #" + testNumber + ":", "YES");
				return;
			}
		}
		out.printLine("Case #" + testNumber + ":", "NO");
	}
}
