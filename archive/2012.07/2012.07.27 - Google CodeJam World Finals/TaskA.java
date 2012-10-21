package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		int[] row = new int[count];
		int[] column = new int[count];
		int[] time = new int[count];
		IOUtils.readIntArrays(in, row, column, time);
		final int[][] minTime = new int[count][count + 1];
		ArrayUtils.fill(minTime, Integer.MAX_VALUE);
		for (int i = 0; i < count; i++) {
			if (time[i] + 1000 >= Math.max(Math.abs(row[i]), Math.abs(column[i])) * 100)
				minTime[i][1] = Math.max(Math.max(Math.abs(row[i]), Math.abs(column[i])) * 100, time[i]);
		}
		int answer = 0;
		for (int killed = 1; killed <= count; killed++) {
			for (int index = 0; index < count; index++) {
				if (minTime[index][killed] == Integer.MAX_VALUE)
					continue;
				answer = Math.max(answer, killed);
				if (killed == count)
					continue;
				for (int i = 0; i < count; i++) {
					int distance = Math.max(750, Math.max(Math.abs(row[i] - row[index]), Math.abs(column[i] - column[index])) * 100);
					if (i != index && minTime[index][killed] + distance < minTime[i][killed + 1] && minTime[index][killed] + distance <= time[i] + 1000)
						minTime[i][killed + 1] = Math.max(minTime[index][killed] + distance, time[i]);
				}
			}
		}
		out.printLine("Case #" + testNumber + ":", answer);
	}
}
