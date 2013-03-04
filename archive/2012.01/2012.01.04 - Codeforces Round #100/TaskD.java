package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] time = IOUtils.readIntArray(in, count);
		Arrays.sort(time);
		int solved = 0;
		int penalty = 0;
		int currentTime = 10;
		for (int i : time) {
			currentTime += i;
			if (currentTime > 720)
				break;
			solved++;
			penalty += Math.max(0, currentTime - 360);
		}
		out.printLine(solved, penalty);
	}
}
