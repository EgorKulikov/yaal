package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int placeCount = in.readInt();
		int carCount = in.readInt();
		int[] cost = IOUtils.readIntArray(in, placeCount);
		int[] weight = IOUtils.readIntArray(in, carCount);
		long answer = 0;
		int[] place = new int[placeCount];
		int[] queue = new int[carCount];
		int begin = 0;
		int end = 0;
		for (int i = 0; i < carCount * 2; i++) {
			int car = in.readInt();
			if (car > 0)
				queue[end++] = car;
			else {
				for (int j = 0; j < placeCount; j++) {
					if (place[j] == -car)
						place[j] = 0;
				}
			}
			for (int j = 0; j < placeCount; j++) {
				if (end > begin && place[j] == 0) {
					place[j] = queue[begin++];
					answer += weight[place[j] - 1] * cost[j];
				}
			}
		}
		out.printLine(answer);
	}
}
