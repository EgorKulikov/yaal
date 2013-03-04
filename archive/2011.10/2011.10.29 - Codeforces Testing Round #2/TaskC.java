package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int day = 0;
		while (day * (day + 1) / 2 <= count)
			day++;
		out.println(day);
		int[][] answer = new int[day][day - 1];
		int index = 1;
		int[] position = new int[day];
		for (int i = 0; i < day; i++) {
			for (int j = i + 1; j < day; j++) {
				answer[i][position[i]++] = index;
				answer[j][position[j]++] = index++;
			}
		}
		for (int[] row : answer)
			IOUtils.printArray(row, out);
	}
}
