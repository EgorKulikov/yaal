package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int startTime = in.readInt();
		int[] opposite = new int[count];
		for (int i = 0; i < count; i++)
			opposite[i] = in.readInt();
		double[] answer = new double[count];
		for (int i = 0; i < count; i++)
			answer[i] = (startTime + opposite[i] + length) / 2d;
		for (int i = 0; i < count; i++)
			out.printLine(answer[i]);
	}
}
