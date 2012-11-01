package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] power = IOUtils.readIntArray(in, count);
		int maxPower = 0;
		int position = 0;
		for (int i = 0; i < count - 2; i++) {
			int currentPower = power[i] + power[i + 1] + power[i + 2];
			if (currentPower > maxPower) {
				maxPower = currentPower;
				position = i + 2;
			}
		}
		out.printLine(maxPower, position);
	}
}
