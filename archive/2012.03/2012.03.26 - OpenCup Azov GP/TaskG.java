package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int calmness = in.readInt();
		int answer = 0;
		int sound = 0;
		for (int i = 0; i < count; i++) {
			sound += in.readInt();
			if (sound >= calmness) {
				answer++;
				sound = 0;
			}
		}
		out.printLine(answer);
	}
}
