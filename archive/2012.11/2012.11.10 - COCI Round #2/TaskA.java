package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int initialPrice = in.readInt();
		int initialWeight = in.readInt();
		double answer = (double) initialPrice / initialWeight;
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			int price = in.readInt();
			int weight = in.readInt();
			answer = Math.min(answer, (double)price / weight);
		}
		out.printLine(answer * 1000);
	}
}
