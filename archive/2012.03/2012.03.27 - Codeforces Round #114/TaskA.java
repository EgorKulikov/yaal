package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int a = in.readInt();
		int d = in.readInt();
		int[] time = new int[count];
		int[] speed = new int[count];
		IOUtils.readIntArrays(in, time, speed);
		double[] answer = new double[count];
		double last = 0;
		for (int i = 0; i < count; i++) {
			double accelerationTime = (double)speed[i] / a;
			if (a * accelerationTime * accelerationTime / 2 >= d)
				answer[i] = time[i] + Math.sqrt(2. * d / a);
			else
				answer[i] = time[i] + accelerationTime + (d - a * accelerationTime * accelerationTime / 2) / speed[i];
			last = answer[i] = Math.max(answer[i], last);
		}
		for (int i = 0; i < count; i++)
			out.printLine(answer[i]);
	}
}
