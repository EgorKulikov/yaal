package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int time = in.readInt() * 2;
		int[] delta = IOUtils.readIntArray(in, count);
		double full = time / length;
		int remaining = time % length;
		double answer = full / 4 * (long)count * (count - 1);
		int j = 0;
		int lap = 0;
		int plus = 0;
		for (int i = 0; i < count; i++) {
			while (delta[j] + lap - delta[i] <= remaining) {
				j++;
				if (j == count) {
					j = 0;
					lap = length;
					plus = count;
				}
			}
			answer += (j - i + plus - 1) / 4d;
		}
		out.printLine(answer);
    }
}
